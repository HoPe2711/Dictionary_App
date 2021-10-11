package com.example.dictionary_ui.controller;

import com.example.dictionary_ui.services.GoogleApiTranslate;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class OnlTranslateController{
  @FXML
  private Button btn_translate;
  @FXML
  private TextArea input_search;
  @FXML
  private Label output_search;

  private ContainerController state;
  private static GoogleApiTranslate googleApiTranslate = new GoogleApiTranslate();

  @FXML
  public void handleEnterInputSearch(ActionEvent event) throws IOException {
    if (event.getSource() == btn_translate) {
      new Thread(() -> {
        String result = null;
        try {
          String searchText = input_search.getText();
          result = googleApiTranslate.translate("en", "vi", searchText);
        } catch (IOException e) {
          e.printStackTrace();
        }
        String finaloutput = result;
        Platform.runLater(() -> {
          output_search.setText(finaloutput);
        });
      }).start();
    }
  }

  public void initData(ContainerController state) {
    this.state = state;
  }
}
