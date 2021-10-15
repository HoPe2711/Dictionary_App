package com.example.dictionary_ui.controller;

import com.example.dictionary_ui.services.TTS;
import com.example.dictionary_ui.services.mysql.SymAntWordService;
import java.util.concurrent.CompletableFuture;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class BookmarkController {
  private ContainerController state;

  @FXML
  private Label view_word_spelling;
  @FXML
  private Label view_word_phonetic;
  @FXML
  private Label view_word_explain;
  @FXML
  private Label view_synonym;
  @FXML
  private Label view_atonym;

  @FXML
  public void onMouseClickSpeak(MouseEvent event) {
    String spelling = view_word_spelling.getText();
    CompletableFuture.runAsync(()-> TTS.speakEnglish(spelling));
  }

  public void initData(ContainerController state) {
    this.state = state;
  }

  public void initData(ContainerController state, String spelling, String phonetic, String explain) {
    this.state = state;
    view_word_spelling.setText(spelling);
    view_word_explain.setText(explain);
    view_word_phonetic.setText(phonetic);
    view_synonym.setText(SymAntWordService.getSynonym(spelling));
    view_atonym.setText(SymAntWordService.getAntonym(spelling));
  }
}
