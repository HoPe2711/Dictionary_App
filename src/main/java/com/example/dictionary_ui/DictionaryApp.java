package com.example.dictionary_ui;

import static com.example.dictionary_ui.controller.ContainerController.dictionaryManagement;
import static com.example.dictionary_ui.data.ConstantVariable.PATH_CONTAINER;
import static com.example.dictionary_ui.data.ConstantVariable.stage_height;
import static com.example.dictionary_ui.data.ConstantVariable.stage_width;

import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DictionaryApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = new File(PATH_CONTAINER).toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Dictionary");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, stage_width, stage_height));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            dictionaryManagement.dictionaryExportToFile();
            Platform.exit();
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}