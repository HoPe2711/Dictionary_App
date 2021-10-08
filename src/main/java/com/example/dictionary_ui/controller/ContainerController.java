package com.example.dictionary_ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContainerController {

    @FXML
    public Button btn_nav_search_off;
    @FXML
    public AnchorPane content_pane;

    private AnchorPane offTranslatePane = null;

    private AnchorPane currentPane;
    private OffTranslateController offTranslateController;

    private void setContentPane(AnchorPane anchorPane) {
        this.content_pane.getChildren().setAll(anchorPane);
        this.currentPane = anchorPane;
    }

    @FXML
    public void handleClickSidebar(ActionEvent event) {
        if (event.getSource() == btn_nav_search_off) {
            showSearchPane();
//        } else if (event.getSource() == btn_nav_add) {
//            showAddPane();
//        } else if (event.getSource() == btn_nav_history) {
//            showHistoryPane();
//        } else if (event.getSource() == btn_nav_bookmark) {
//            showBookmarkPane();
//        } else if (event.getSource() == btn_nav_edit) {
//            showEditPane();
        }
    }

    public void resetStyleNav() {
        btn_nav_search_off.setStyle(null);
//        btn_nav_add.setStyle(null);
//        btn_nav_history.setStyle(null);
//        btn_nav_bookmark.setStyle(null);
//        btn_nav_edit.setStyle(null);
    }

    public void showSearchPane() {
        this.setContentPane(offTranslatePane);
        offTranslateController.initData(this);
        this.resetStyleNav();
        btn_nav_search_off.setStyle("-fx-background-color:  #394357;");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("com/example/dictionary_ui/offline-translate.fxml"));
            offTranslatePane = fxmlLoader.load();
            offTranslateController = fxmlLoader.getController();
            offTranslateController.initData(this);
        } catch (IOException e) {
            System.out.println("Error load search_pane.");
        }

        this.showSearchPane();
    }
}
