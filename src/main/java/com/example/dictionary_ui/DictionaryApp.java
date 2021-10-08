package com.example.dictionary_ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class DictionaryApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = new File("src/main/resources/com/example/dictionary_ui/container.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Dictionary");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 1177, 853));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}