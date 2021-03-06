package com.example.dictionary_ui.controller;

import com.example.dictionary_ui.services.TTS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.concurrent.CompletableFuture;

public class ViewWordOffline {

    @FXML
    private Button btn_delete_word, btn_put_word, btn_bookmark;
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

    @FXML
    public void handlePutWord(ActionEvent event){
        if (event.getSource() == btn_put_word) {
            this.state.state.showPutPane(view_word_spelling.getText(), view_word_phonetic.getText(), view_word_explain.getText());
        }
    }

    @FXML
    public void handleBookmark(ActionEvent event){
        if (event.getSource() == btn_bookmark) {
            this.state.state.showBookmarkPane(view_word_spelling.getText(), view_word_phonetic.getText(), view_word_explain.getText());
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
