package com.example.courierservice.mas_projekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        switchScene("ekran-oceny-dostawy.fxml"); //ekran-glowny-logowania.fxml
    }

    public static void switchScene(String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource(fxmlFile));
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            primaryStage.centerOnScreen();
            primaryStage.setTitle("Pocztex - aplikacja kurierska");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Błąd przy ładowaniu: " + fxmlFile);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}