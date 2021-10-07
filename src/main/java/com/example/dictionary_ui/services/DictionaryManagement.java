package com.example.dictionary_ui.services;

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

    public DictionaryManagement(Dictionary dictionary, TrieNode trieNode) {
        this.dictionary = dictionary;
        this.trieNode = trieNode;
    }

    private Word dictionaryLookup(String word) {
        return this.dictionary.getDictionary().get(word);
    }

    private void getResult(Set<String> keys) {
        System.out.printf("%-4s|%-45s|%s\n", "No", "English", "Tieng Viet");
        int order = 0;
        for (String key : keys) {
            String no = String.valueOf(++order);
            Word word = this.dictionary.getDictionary().get(key);
            String word_target = word.getWord_target();
            String word_explain = word.getWord_explain().toString();
            System.out.printf("%-4s%s|%-45s|%s\n", no, word_target, word.getPhonetics(), word_explain);
        }
    }

    public void getFromCommandLine() {
        getResult(this.dictionary.getDictionary().keySet());
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
            System.out.println(this.dictionary.getDictionary().keySet().size());
            trieNode.buildTrie(this.dictionary.getDictionary().keySet());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addWordToDictionary(Word word) {
        if (dictionaryLookup(word.getWord_target()) != null) System.out.println("Tu da ton tai");
        else {
            this.dictionary.addWord(word);
            trieNode.insert(word.getWord_target());
        }
    }

    public void deleteWordToDictionary(String word) {
        if (dictionaryLookup(word) == null) System.out.println("Tu khong ton tai");
        else {
            this.dictionary.deleteWord(word);
            trieNode.delete(word);
        }
    }

    public void putWordToDictionary(Word word) {
        if (dictionaryLookup(word.getWord_target()) == null) System.out.println("Tu khong ton tai");
        else this.dictionary.putaddWord(word);
    }

    public void dictionarySearchPattern(String pattern) {
        getResult(trieNode.findAllWords(pattern));
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
            System.out.printf("Xuat file %s thanh cong\n", ConstantVariable.PATH);
        } catch (IOException e) {
            System.err.println("\nLoi: Khong ghi duoc file");
        }
    }
}
