package com.example.dictionary_ui.controller;

import static com.example.dictionary_ui.data.ConstantVariable.PATH_VIEW_WORD;

import com.example.dictionary_ui.entity.Word;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
    if (spelling == null) {
      return;
    }
    Word word = state.getDictionaryManagement()
        .dictionaryLookup(spelling);
    viewWordOffline.initData(this, word.getWord_target(),
        word.getWord_explain().toString(),
        word.getPhonetics());
  }

  public void actionSearch(String spelling) {
    Set<String> stringWords = this.state.getDictionaryManagement()
        .dictionarySearchPattern(spelling);
    search_list_view.getItems().setAll(stringWords);
    if (stringWords.size() == 0) {
      viewWordOffline.initData(this, "Your word doesn't exist", "", "");
    } else {
      Word word = state.getDictionaryManagement()
          .dictionaryLookup(stringWords.iterator().next());
      if (word != null) {
        viewWordOffline.initData(this, word.getWord_target(),
            word.getWord_explain().toString(),
            word.getPhonetics());
      }
    }
  }

  @FXML
  public void handleChangeInputSearch(KeyEvent event) {
    if (event.getSource() == input_search) {
      new Thread(()->{
        String searchText = input_search.getText();
        Platform.runLater(()->actionSearch(searchText));
      }).start();
    }
  }

  public void reload() {
    if (state == null) {
      return;
    }
    String searchText = input_search.getText();
    actionSearch(searchText);
  }

  public void initData(ContainerController state) {
    this.state = state;
    this.reload();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(
        getClass().getResource(PATH_VIEW_WORD));
    AnchorPane viewWordVBox;
    try {
      viewWordVBox = fxmlLoader.load();
    } catch (IOException e) {
      System.out.println("Error load view word pane.");
      return;
    }
    right_content.getChildren().addAll(viewWordVBox);
    viewWordOffline = fxmlLoader.getController();
    viewWordOffline.initData(this, "Word", "", "");
  }
}
