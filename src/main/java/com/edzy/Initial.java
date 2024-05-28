package com.edzy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Initial extends Application {
    


    @Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) throws Exception {
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/edzy/Edzy-logo1.png")));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/edzy/Edzy-logo1.png")));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/edzy/Edzy-logo1.png")));
        Parent root = FXMLLoader.load(getClass().getResource("/com/edzy/Initial.fxml"));
        primaryStage.setTitle("Initial Window");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
