package com.example.dictionary_ui;

import static com.example.dictionary_ui.controller.ContainerController.dictionaryManagement;
import static com.example.dictionary_ui.data.ConstantVariable.ICON;
import static com.example.dictionary_ui.data.ConstantVariable.PATH_CONTAINER;
import static com.example.dictionary_ui.data.ConstantVariable.stage_height;
import static com.example.dictionary_ui.data.ConstantVariable.stage_width;

import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DictionaryApp extends Application {
    public static HostServices hostServices;


    @Override
    public void start(Stage primaryStage) throws Exception {
        hostServices = getHostServices();
        URL url = new File(PATH_CONTAINER).toURI().toURL();
        URL icon = new File(ICON).toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Dictionary");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, stage_width, stage_height));
        primaryStage.getIcons().add(new Image(String.valueOf(icon)));
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