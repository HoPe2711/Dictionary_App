package com.example.dictionary_ui.controller;

import static com.example.dictionary_ui.data.ConstantVariable.DRAGON_PATH;
import static com.example.dictionary_ui.data.ConstantVariable.TIGER_PATH;

import com.example.dictionary_ui.DictionaryApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Border;

public class AuthorController {
  private ContainerController state;

  @FXML
  private Hyperlink TigerGit;
  @FXML
  private Hyperlink DragonGit;

  @FXML
  public void handleLink(ActionEvent event) {
    if(event.getSource() == TigerGit) {
      TigerGit.setText(TIGER_PATH);
      DictionaryApp.hostServices.showDocument(TIGER_PATH);
      TigerGit.setBorder(Border.EMPTY);
    }
    else if(event.getSource() == DragonGit) {
      DragonGit.setText(DRAGON_PATH);
      DictionaryApp.hostServices.showDocument(DRAGON_PATH);
      DragonGit.setBorder(Border.EMPTY);
    }
  }


  public void initData(ContainerController state) {
    this.state = state;
  }
}
