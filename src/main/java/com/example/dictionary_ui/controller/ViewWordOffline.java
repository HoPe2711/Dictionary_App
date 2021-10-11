package com.example.dictionary_ui.controller;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.util.concurrent.CompletableFuture;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ViewWordOffline {
    @FXML
    private Label view_word_spelling;
    @FXML
    private Label view_word_phonetic;
    @FXML
    private Label view_word_explain;
    private ContainerController state;

    private void speakEnglish(String spelling){
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice;

        voice = VoiceManager.getInstance().getVoice("kevin");
        if (voice != null) {
            voice.allocate();
        }
        try {
            voice.setRate(140);
            voice.setPitch(100);
            voice.setVolume(100);
            voice.speak(spelling);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onMouseClickSpeak(MouseEvent event) {
        String spelling = view_word_spelling.getText();
        CompletableFuture.runAsync(()->speakEnglish(spelling));
    }

    public void initData(ContainerController state, String spelling, String explain, String phonetic) {
        this.state = state;
        view_word_spelling.setText(spelling);
        view_word_explain.setText(explain);
        if(phonetic.equals("/null/"))
            phonetic = null;
        view_word_phonetic.setText(phonetic);
    }
}
