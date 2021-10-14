package com.example.dictionary_ui.controller;

import com.example.dictionary_ui.entity.Word;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddWordController implements Initializable {
  private OffTranslateController state;
  private String phonetic;
  @FXML
  private Button btn_add_word, btn_put_word;
  @FXML
  protected TextField word;
  @FXML
  protected TextArea explain;

  @FXML
  public void handleEnterAddWord(ActionEvent event){
    if (event.getSource() == btn_add_word) {
      Word add_word = new Word(word.getText().trim(), explain.getText().trim());
      if (this.state.state.getDictionaryManagement().addWordToDictionary(add_word)){
        this.state.input_search.setText(word.getText().trim());
        this.state.state.showSearchPane();
      }
    }
  }

  @FXML
  public void handleEnterPutWord(ActionEvent event){
    if (event.getSource() == btn_put_word) {
      Word add_word = new Word(word.getText().trim(), explain.getText().trim(), this.phonetic);
      if (this.state.state.getDictionaryManagement().putWordToDictionary(add_word)){
        this.state.input_search.setText(word.getText().trim());
        this.state.state.showSearchPane();
      }
    }
  }

  public void initData(OffTranslateController state) {
    this.state = state;
  }

  public void initData(OffTranslateController state, String word, String phonetic, String explain) {
    this.state = state;
    this.word.setText(word);
    this.explain.setText(explain);
    this.phonetic = phonetic;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
  }
}
