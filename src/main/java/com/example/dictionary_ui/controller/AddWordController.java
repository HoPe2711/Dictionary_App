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
  private ContainerController state;
  @FXML
  private Button btn_add_word;
  @FXML
  protected TextField word;
  @FXML
  protected TextArea explain;

  @FXML
  public void handleEnterAddWord(ActionEvent event){
    if (event.getSource() == btn_add_word) {
      Word add_word = new Word(word.getText().trim(), explain.getText().trim());
      this.state.getDictionaryManagement().addWordToDictionary(add_word);
    }
  }

  public void initData(ContainerController state) {
    this.state = state;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
  }
}
