package com.example.dictionary_ui.controller;

import com.example.dictionary_ui.services.GoogleApiTranslate;
import com.example.dictionary_ui.services.TTS;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class OnlTranslateController{
  @FXML
  private Button btn_translate;
  @FXML
  private TextArea input_search;
  @FXML
  private Label output_search;

  private ContainerController state;
  private static final GoogleApiTranslate googleApiTranslate = new GoogleApiTranslate();

  @FXML
  public void onMouseClickSpeak(MouseEvent event) {
    String spelling = input_search.getText();
    CompletableFuture.runAsync(()-> TTS.speakEnglish(spelling));
  }

  @FXML
  public void handleEnterInputSearch(ActionEvent event) throws IOException {
    if (event.getSource() == btn_translate) {
      new Thread(() -> {
        StringBuilder result = new StringBuilder();
        try {
          String searchText = input_search.getText();
          for (String paragraph: searchText.split("\n")){
            result.append(googleApiTranslate.translate("en", "vi", paragraph));
            result.append("\n");
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
        String finaloutput = result.toString();
        Platform.runLater(() -> output_search.setText(finaloutput));
      }).start();
    }
  }

  public void initData(ContainerController state) {
    this.state = state;
  }
}
