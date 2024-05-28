package com.edzy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Fedora extends Application {

    @Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/edzy/Fedora.fxml"));
        Parent root = loader.load();
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/edzy/Edzy-logo1.png")));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/edzy/Edzy-logo1.png")));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/edzy/Edzy-logo1.png")));
        primaryStage.setTitle("Edzy (Fedora)");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            openInitialWindow();
        });
    }

    private void openInitialWindow() {
        try {
            Stage initialStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/com/edzy/Initial.fxml"));
            initialStage.setScene(new Scene(root));
            initialStage.setResizable(false);
            initialStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/edzy/Edzy-logo1.png")));
            initialStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/edzy/Edzy-logo1.png")));
            initialStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/edzy/Edzy-logo1.png")));
            initialStage.setTitle("Edzy");
            initialStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
