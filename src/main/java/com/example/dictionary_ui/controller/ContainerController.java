package com.example.dictionary_ui.controller;

import com.example.dictionary_ui.services.DictionaryManagement;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ContainerController implements Initializable {

    @FXML
    public Button btn_nav_search_off, btn_nav_search_on, btn_add_word;
    @FXML
    public AnchorPane content_pane;

    public static DictionaryManagement dictionaryManagement = new DictionaryManagement();
    private AnchorPane offTranslatePane = null;
    private AnchorPane onTranslatePane = null;
    private AnchorPane addWordPane = null;
    private AnchorPane currentPane;
    private OffTranslateController offTranslateController;
    private OnlTranslateController onlTranslateController;
    private AddWordController addWordController;

    public DictionaryManagement getDictionaryManagement() {
        return dictionaryManagement;
    }

    private void setContentPane(AnchorPane anchorPane) {
        this.content_pane.getChildren().setAll(anchorPane);
        this.currentPane = anchorPane;
    }

    @FXML
    public void handleClickSidebar(ActionEvent event) {
        if (event.getSource() == btn_nav_search_off) {
            showSearchPane();
        } else if (event.getSource() == btn_nav_search_on) {
            showOnlineSearchPane();
        } else if (event.getSource() == btn_add_word) {
            showAddPane();
//        } else if (event.getSource() == btn_nav_bookmark) {
//            showBookmarkPane();
//        } else if (event.getSource() == btn_nav_edit) {
//            showEditPane();
        }
    }

    public void resetStyleNav() {
        btn_nav_search_off.setStyle(null);
        btn_nav_search_on.setStyle(null);
//        btn_nav_history.setStyle(null);
//        btn_nav_bookmark.setStyle(null);
//        btn_nav_edit.setStyle(null);
    }

    public void showSearchPane() {
        this.setContentPane(offTranslatePane);
        offTranslateController.initData(this);
//        this.resetStyleNav();
//        btn_nav_search_off.setStyle("-fx-background-color:  #13109c;");
    }

    public void showOnlineSearchPane() {
        this.setContentPane(onTranslatePane);
        onlTranslateController.initData(this);
//        this.resetStyleNav();
//        btn_nav_search_off.setStyle("-fx-background-color:  #13109c;");
    }

    public void showAddPane() {
        this.setContentPane(addWordPane);
        addWordController.initData(this.offTranslateController);
//        this.resetStyleNav();
//        btn_nav_search_off.setStyle("-fx-background-color:  #13109c;");
    }

    public void showPutPane(String word, String explain){
        this.setContentPane(addWordPane);
        addWordController.initData(this.offTranslateController, word, explain);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/dictionary_ui/offline-translate.fxml"));
            offTranslatePane = fxmlLoader.load();
            offTranslateController = fxmlLoader.getController();
            offTranslateController.initData(this);
        } catch (IOException e) {
            System.out.println("Error load search_pane.");
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/dictionary_ui/online-translate.fxml"));
            onTranslatePane = fxmlLoader.load();
            onlTranslateController = fxmlLoader.getController();
            onlTranslateController.initData(this);
        } catch (IOException e) {
            System.out.println("Error load add_pane pane.");
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/dictionary_ui/add-word.fxml"));
            addWordPane = fxmlLoader.load();
            addWordController = fxmlLoader.getController();
            addWordController.initData(this.offTranslateController);
        } catch (IOException e) {
            System.out.println("Error load add_pane pane.");
        }
        this.showSearchPane();
    }
}
