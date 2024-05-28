package com.edzy;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InitialController {

    @FXML
    private ImageView imageView;

    @FXML
    void DBTNclicked(MouseEvent event) {
        System.out.println("Debian button clicked");
        openDebianWindow();
    }

    @FXML
    void FBTNclicked(MouseEvent event) {
        openFedoraWindow();
    }

    private void openFedoraWindow() {
        Platform.runLater(() -> {
            try {
                // Close the current window
                imageView.getScene().getWindow().hide();
                
                // Open the Debian window
                Fedora fedora = new Fedora();
                Stage fedoraStage = new Stage();
                fedora.start(fedoraStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void openDebianWindow() {
        Platform.runLater(() -> {
            try {
                // Close the current window
                imageView.getScene().getWindow().hide();
                
                // Open the Debian window
                Debian debian = new Debian();
                Stage debianStage = new Stage();
                debian.start(debianStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
