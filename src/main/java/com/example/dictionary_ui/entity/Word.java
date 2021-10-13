package com.example.dictionary_ui.entity;

public class Word {

    private String word_target;
    private Meaning word_explain;
    private String phonetics;

    public Word() {
        this.word_explain = new Meaning();
    }

    public Word(String word, String explain) {
        this.word_explain = new Meaning();
        addToMeaning(explain);
        this.word_target = word;
        this.phonetics = "/null/";
    }

    public Word(String word_target, Meaning word_explain, String phonetics) {
        this.word_target = word_target;
        this.word_explain = word_explain;
        this.phonetics = phonetics;
    }

    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public Meaning getWord_explain() {
        return word_explain;
    }

    public String getPhonetics() {
        return phonetics;
    }

    public void setPhonetics(String phonetics) {
        this.phonetics = phonetics;
    }

    public void addToMeaning(String s) {
        this.word_explain.Add(s);
    }
}
