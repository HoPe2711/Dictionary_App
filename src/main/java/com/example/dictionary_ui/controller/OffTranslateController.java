package com.example.dictionary_ui.controller;

import com.example.dictionary_ui.entity.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class OffTranslateController implements Initializable {
    protected ContainerController state;

    @FXML
    protected TextField input_search;
    @FXML
    protected ListView<String> search_list_view;
    @FXML
    protected AnchorPane right_content;
    protected ViewWordOffline viewWordOffline;

    @FXML
    public void handleEnterInputSearch(ActionEvent event) {
        if (event.getSource() == input_search) {
            System.out.println("Enter input search");
        }
    }

    @FXML
    public void handleSelectItemListView(MouseEvent event) {
        String spelling = search_list_view.getSelectionModel().getSelectedItem();
        if (spelling == null) return;
        input_search.setText(spelling);
        actionSearch(spelling);
    }

    public void actionSearch(String spelling) {
        Set<String> stringWords = this.state.getDictionaryManagement().dictionarySearchPattern(spelling);
        search_list_view.getItems().setAll(stringWords);

        Word word = state.getDictionaryManagement().dictionaryLookup(spelling);
        if (word != null)
            viewWordOffline.initData(this.state, word.getWord_target(), word.getWord_explain().toString());
    }

    @FXML
    public void handleChangeInputSearch(KeyEvent event) {
        if (event.getSource() == input_search) {
            String searchText = input_search.getText();
            if (!searchText.isEmpty()) {
                actionSearch(searchText);
            } else {
                search_list_view.getItems().clear();
            }
        }
    }

    public void reset() {
        input_search.setText("");
        search_list_view.getItems().clear();
        viewWordOffline.initData(this.state, "", "");
    }

    public void reload() {
        if (state == null) return;

        String searchText = input_search.getText();
        if (!searchText.isEmpty()) {
            actionSearch(searchText);
        } else {
            search_list_view.getItems().clear();
        }

        //viewWordOffline.reload();
    }

    protected void loadViewWord(String spelling, String explain) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/dictionary_ui/view-word-offline.fxml"));
        AnchorPane viewWordVBox;
        try {
            viewWordVBox = fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Error load view word pane.");
            return;
        }
        right_content.getChildren().addAll(viewWordVBox);
        viewWordOffline= fxmlLoader.getController();
        viewWordOffline.initData(this.state, spelling, explain);
    }

    public void initData(ContainerController state) {
        this.state = state;
        if (viewWordOffline == null) {
            loadViewWord("", "");
        }

        this.reload();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        loadViewWord("Spelling", "Explain");
    }
}
