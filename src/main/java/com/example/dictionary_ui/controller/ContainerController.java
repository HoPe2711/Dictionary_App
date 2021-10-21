package com.example.dictionary_ui.controller;

import com.example.dictionary_ui.services.DictionaryManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.dictionary_ui.data.ConstantVariable.*;

public class ContainerController implements Initializable {

    @FXML
    public Button btn_nav_search_off, btn_nav_search_on, btn_add_word, btn_bookmark, btn_author;
    @FXML
    public AnchorPane content_pane;

    public static DictionaryManagement dictionaryManagement = new DictionaryManagement();
    private AnchorPane offTranslatePane = null;
    private AnchorPane onTranslatePane = null;
    private AnchorPane addWordPane = null;
    private AnchorPane bookmarkPane = null;
    private AnchorPane authorPane = null;
    private OffTranslateController offTranslateController;
    private OnlTranslateController onlTranslateController;
    private AddWordController addWordController;
    private BookmarkController bookmarkController;
    private AuthorController authorController;

    public DictionaryManagement getDictionaryManagement() {
        return dictionaryManagement;
    }

    private void setContentPane(AnchorPane anchorPane) {
        this.content_pane.getChildren().setAll(anchorPane);
    }

    @FXML
    public void handleClickSidebar(ActionEvent event) {
        if (event.getSource() == btn_nav_search_off) {
            showSearchPane();
        } else if (event.getSource() == btn_nav_search_on) {
            showOnlineSearchPane();
        } else if (event.getSource() == btn_add_word) {
            showAddPane();
        } else if (event.getSource() == btn_bookmark) {
            showBookmarkPane();
        } else if (event.getSource() == btn_author) {
            showAuthorPane();
        }
    }

    private void resetStyleNav() {
        btn_nav_search_off.setStyle("-fx-background-color:transparent;");
        btn_nav_search_on.setStyle("-fx-background-color:transparent;");
        btn_bookmark.setStyle("-fx-background-color:transparent;");
        btn_add_word.setStyle("-fx-background-color:transparent;");
        btn_author.setStyle("-fx-background-color:transparent;");
    }

    private void showAuthorPane() {
        this.setContentPane(authorPane);
        authorController.initData(this);
        this.resetStyleNav();
        btn_author.setStyle("-fx-background-color: #213960;");
    }

    public void showSearchPane() {
        this.setContentPane(offTranslatePane);
        offTranslateController.initData(this);
        this.resetStyleNav();
        btn_nav_search_off.setStyle("-fx-background-color: #213960;");
    }

    public void showOnlineSearchPane() {
        this.setContentPane(onTranslatePane);
        onlTranslateController.initData(this);
        this.resetStyleNav();
        btn_nav_search_on.setStyle("-fx-background-color: #213960;");
    }

    public void showAddPane() {
        this.setContentPane(addWordPane);
        addWordController.initData(this.offTranslateController);
        this.resetStyleNav();
        btn_add_word.setStyle("-fx-background-color:  #213960;");
    }

    public void showPutPane(String word, String phonetic, String explain){
        this.setContentPane(addWordPane);
        addWordController.initData(this.offTranslateController, word, phonetic, explain);
        this.resetStyleNav();
        btn_add_word.setStyle("-fx-background-color:  #213960;");
    }

    public void showBookmarkPane(){
        this.setContentPane(bookmarkPane);
        bookmarkController.initData(this);
        this.resetStyleNav();
        btn_bookmark.setStyle("-fx-background-color:  #213960;");
    }

    public void showBookmarkPane(String word, String phonetic, String explain) {
        this.setContentPane(bookmarkPane);
        bookmarkController.initData(this,word, phonetic, explain);
        this.resetStyleNav();
        btn_bookmark.setStyle("-fx-background-color:  #213960;");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(PATH_OFF_DIC));
            offTranslatePane = fxmlLoader.load();
            offTranslateController = fxmlLoader.getController();
            offTranslateController.initData(this);
        } catch (IOException e) {
            System.out.println("Error load search_pane.");
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(PATH_ON_DIC));
            onTranslatePane = fxmlLoader.load();
            onlTranslateController = fxmlLoader.getController();
            onlTranslateController.initData(this);
        } catch (IOException e) {
            System.out.println("Error load add_pane pane.");
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(PATH_ADD_WORD));
            addWordPane = fxmlLoader.load();
            addWordController = fxmlLoader.getController();
            addWordController.initData(this.offTranslateController);
        } catch (IOException e) {
            System.out.println("Error load add_pane pane.");
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(PATH_BOOKMARK));
            bookmarkPane = fxmlLoader.load();
            bookmarkController = fxmlLoader.getController();
            bookmarkController.initData(this);
        } catch (IOException e) {
            System.out.println("Error load add_pane pane.");
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(PATH_AUTHOR));
            authorPane = fxmlLoader.load();
            authorController = fxmlLoader.getController();
            authorController.initData(this);
        } catch (IOException e) {
            System.out.println("Error load author_pane pane.");
        }

        this.showSearchPane();
    }
}
