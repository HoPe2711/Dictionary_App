package com.example.dictionary_ui.entity;

public class Meaning {
    private StringBuilder meaning;

    public Meaning() {
        this.meaning = new StringBuilder();
    }

    @Override
    public String toString() {
        return meaning + "";
    }

    public void Add(String s) {
        meaning.append(s).append("\n");
    }
}