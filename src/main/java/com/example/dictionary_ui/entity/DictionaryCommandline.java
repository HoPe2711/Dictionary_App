package com.example.dictionary_ui.entity;

import com.example.dictionary_ui.services.DictionaryManagement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DictionaryCommandline {

    private DictionaryManagement dictionaryManagement;

    public DictionaryCommandline(DictionaryManagement dictionaryManagement) {
        this.dictionaryManagement = dictionaryManagement;
    }

    public void showAllWords() {
        this.dictionaryManagement.getFromCommandLine();
    }

    public void dictionarySearch(String pattern) {
        this.dictionaryManagement.dictionarySearchPattern(pattern);
    }

    public void dictionaryAdvance() {
        this.dictionaryManagement.loadFromFile();
        Scanner sc = new Scanner(System.in);
        while (true) {
            int option = -1;
            System.out.println("\nSu dung:  1. Them tu  |  2. Sua tu  |  3. Xoa tu  |  4. Tim tu  |  5. Xuat ra file| 6.Show All Word");
            System.out.print("Nhap lua chon: ");
            try {
                option = sc.nextInt();
            } catch (InputMismatchException ignored) {
            }
            sc.nextLine();
            switch (option) {
                case 1 -> {
                    System.out.println("Nhap vao tu moi:");
                    String word = sc.nextLine().trim();
                    System.out.println("Nhap vao giai nghia:");
                    String explain = sc.nextLine() + "\n";
                    this.dictionaryManagement.addWordToDictionary(new Word(word, explain));
                }
                case 2 -> {
                    System.out.println("Nhap vao tu can sua:");
                    String word = sc.nextLine();
                    System.out.println("Nhap vao giai nghia:");
                    String explain = sc.nextLine() + "\n";
                    this.dictionaryManagement.putWordToDictionary(new Word(word, explain));
                }
                case 3 -> {
                    System.out.print("Nhap tu can xoa: ");
                    String word = sc.nextLine();
                    this.dictionaryManagement.deleteWordToDictionary(word);
                }
                case 4 -> {
                    System.out.print("Nhap tu can tim: ");
                    String pattern = sc.nextLine();
                    dictionarySearch(pattern);
                }
                case 5 -> {
                    this.dictionaryManagement.dictionaryExportToFile();
                    System.exit(0);
                }
                case 6 -> this.showAllWords();
                default -> System.out.println("Nhap lua chon trong khoang 1-6");
            }
        }
    }

//    public static void main(String[] args) {
//        new DictionaryCommandline(new DictionaryManagement(new Dictionary(), new TrieNode())).dictionaryAdvance();
//    }
}
