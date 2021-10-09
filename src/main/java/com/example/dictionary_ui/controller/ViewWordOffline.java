package com.example.dictionary_ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ViewWordOffline {
    @FXML
    private Label view_word_spelling;
    @FXML
    private Label view_word_explain;
    private ContainerController state;

    public void initData(ContainerController state, String spelling, String explain) {
        this.state = state;
        view_word_spelling.setText(spelling);
        view_word_explain.setText(explain);
    }
}
