package com.example.dictionary_ui.controller;

import com.example.dictionary_ui.services.TTS;
import java.util.concurrent.CompletableFuture;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ViewWordOffline {

    @FXML
    private Button btn_delete_word;
    @FXML
    private Label view_word_spelling;
    @FXML
    private Label view_word_phonetic;
    @FXML
    private Label view_word_explain;
    private OffTranslateController state;

    @FXML
    public void onMouseClickSpeak(MouseEvent event) {
        String spelling = view_word_spelling.getText();
        CompletableFuture.runAsync(()-> TTS.speakEnglish(spelling));
    }

    @FXML
    public void handleDeleteWord(ActionEvent event){
        if (event.getSource() == btn_delete_word) {
            this.state.state.getDictionaryManagement().deleteWordToDictionary(view_word_spelling.getText());
            this.state.reload();
        }
    }

    public void initData(OffTranslateController state, String spelling, String explain, String phonetic) {
        this.state = state;
        view_word_spelling.setText(spelling);
        view_word_explain.setText(explain);
        if(phonetic.equals("/null/"))
            phonetic = null;
        view_word_phonetic.setText(phonetic);
    }
}
