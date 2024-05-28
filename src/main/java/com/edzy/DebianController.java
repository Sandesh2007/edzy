package com.edzy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class DebianController {
    @FXML
    private TextArea textArea;

    private File selectedFile;

    private static String LOG_FILE_DIR;

    @FXML
    void initialize() {
        try {
            // Set the log file directory to the user's home directory
            String username = getUsername();
            if (username != null) {
                LOG_FILE_DIR = "/home/" + username + "/";
            } else {
                textArea.setText("Failed to retrieve the username.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            textArea.setText("An error occurred while setting up the log directory.");
        }
    }

    @FXML
    void onSelectClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Debian Package");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Debian Packages", "*.deb"));

        Stage stage = (Stage) textArea.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            // Display file information
            String fileName = selectedFile.getName();
            String filePath = selectedFile.getAbsolutePath();
            if (fileName.length() > 55) {
                fileName = fileName.substring(0, 55) + "\n " + fileName.substring(55);
            }
            if (filePath.length() > 65) {
                filePath = filePath.substring(0, 65) + "\n " + filePath.substring(65);
            }
            double fileSizeMB = (double) selectedFile.length() / (1024 * 1024);
            textArea.setText("File name: " + fileName + "\n" + "\n"
                    + "File location: " + filePath + "\n" + "\n"
                    + String.format("File size: %.2f MB", fileSizeMB));
        }
    }

    @FXML
    void onInstallClick(ActionEvent event) {
        if (selectedFile != null) {
            try {
                // Get the current user's username
                String username = getUsername();
                if (username != null) {
                    // Construct the command with the full path to the .deb file
                    String debFilePath = selectedFile.getPath();
                    String logFilePath = LOG_FILE_DIR + "/error_log.txt";
                    String command = "pkexec sudo dpkg -i " + debFilePath;
                    executeCommand(command, logFilePath);
                } else {
                    textArea.setText("Failed to retrieve the username.");
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                String logFilePath = LOG_FILE_DIR + "/error_log.txt";
                logException(e, logFilePath);
                textArea.setText("An error occurred while executing the command.\nLog File: " + logFilePath);
            }
        } else {
            textArea.setText("No file selected. Please select a Debian package first.");
        }
    }

    private String getUsername() throws IOException, InterruptedException {
        String[] whoamiCmd = {"whoami"};
        Process whoamiProcess = Runtime.getRuntime().exec(whoamiCmd);
        BufferedReader whoamiReader = new BufferedReader(new InputStreamReader(whoamiProcess.getInputStream()));
        String username = whoamiReader.readLine().trim();
        whoamiReader.close();
        whoamiProcess.waitFor();
        return username;
    }

    private void executeCommand(String command, String logFilePath) {
        ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", command);
        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                textArea.setText("Command executed successfully.");
            } else {
                logError(process, logFilePath);
                textArea.setText("Command execution failed. Please check the log file for more details.\nLog File: " + logFilePath);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            logException(e, logFilePath);
            textArea.setText("An error occurred while executing the command.\nLog File: " + logFilePath);
        }
    }

    private void logError(Process process, String logFilePath) throws IOException {
        ensureLogDirectoryExists();
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFilePath, true))) {
            writer.println("----- ERROR LOG -----");
            writer.println("Timestamp: " + getCurrentTimeStamp());
            writer.println("Command: " + process.info().commandLine().orElse("N/A"));

            // Read the error output from the process
            try (var errorStream = process.getInputStream();
                 var reader = new BufferedReader(new InputStreamReader(errorStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.println(line);
                }
            }

            writer.println("----------------------");
        }
    }

    private void logException(Exception e, String logFilePath) {
        ensureLogDirectoryExists();
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFilePath, true))) {
            writer.println("----- EXCEPTION LOG -----");
            writer.println("Timestamp: " + getCurrentTimeStamp());
            e.printStackTrace(writer);
            writer.println("--------------------------");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private String getCurrentTimeStamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    private void ensureLogDirectoryExists() {
        File logDir = new File(LOG_FILE_DIR);
        if (!logDir.exists()) {
            logDir.mkdirs();
        }
    }
}
