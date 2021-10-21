package com.example.dictionary_ui.services;

import com.example.dictionary_ui.controller.Notification;
import com.example.dictionary_ui.data.ConstantVariable;
import com.example.dictionary_ui.entity.Dictionary;
import com.example.dictionary_ui.entity.Word;
import com.example.dictionary_ui.services.trie.TrieNode;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class DictionaryManagement {

    private Dictionary dictionary;
    private TrieNode trieNode;

    public DictionaryManagement() {
        this.dictionary = new Dictionary();
        this.trieNode = new TrieNode();
        this.loadFromFile();
    }

    public Word dictionaryLookup(String word) {
        return this.dictionary.getDictionary().get(word);
    }

    public void loadFromFile() {
        try {
            Path path = Paths.get(ConstantVariable.PATH);
            List<String> dataList = Files.readAllLines(path);
            ListIterator<String> itr = dataList.listIterator();

            Word word = new Word();
            while (itr.hasNext()) {
                String p = itr.next();

                if (p.startsWith("@")) {
                    word = new Word();
                    String[] part = p.split(" /", 2);

                    String s2 = part[0].substring(1).trim();
                    word.setWord_target(s2);

                    if (part.length < 2) {
                        word.setPhonetics("/null/");
                    } else
                        word.setPhonetics("/" + part[1]);
                    while (itr.hasNext()) {
                        String p1 = itr.next();

                        if (!p1.startsWith("@")) {
                            word.addToMeaning(p1);

                        } else {
                            this.dictionary.addWord(word);
                            itr.previous();
                            break;
                        }
                    }
                }
            }
            this.dictionary.addWord(word);
            trieNode.buildTrie(this.dictionary.getDictionary().keySet());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addWordToDictionary(Word word) {
        if (dictionaryLookup(word.getWord_target()) != null) Notification.errorAction("Your word exists!");
        else {
            this.dictionary.addWord(word);
            trieNode.insert(word.getWord_target());
            Notification.acceptAction();
            return true;
        }
        return false;
    }

    public void deleteWordToDictionary(String word) {
        if (dictionaryLookup(word) == null) Notification.errorAction("Your word doesn't exists!");
        else {
            this.dictionary.deleteWord(word);
            trieNode.delete(word);
            Notification.acceptAction();
        }
    }

    public boolean putWordToDictionary(Word word) {
        if (dictionaryLookup(word.getWord_target()) == null) Notification.errorAction("Your word doesn't exists!");
        else {
            this.dictionary.putaddWord(word);
            Notification.acceptAction();
            return true;
        }
        return false;
    }

    public Set<String> dictionarySearchPattern(String pattern) {
        return trieNode.findAllWords(pattern);
    }

    public void dictionaryExportToFile() {
        PrintWriter pw;
        try {
            pw = new PrintWriter(ConstantVariable.PATH);
            for (String key : this.dictionary.getDictionary().keySet()) {
                Word word = this.dictionary.getDictionary().get(key);
                pw.printf("%s%s\n", "@" + word.getWord_target() + " ", word.getPhonetics());
                pw.printf("%s", word.getWord_explain());
            }
            pw.flush();
            System.out.print("Xuat file thanh cong\n");
        } catch (IOException e) {
            System.err.println("\nLoi: Khong ghi duoc file");
        }
    }
}
