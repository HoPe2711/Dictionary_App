import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
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
            String word_explain = word.getWord_explain();
            System.out.printf("%-4s|%-45s|%s\n", no, word_target, word_explain);
        }
    }

    public void getFromCommandLine() {
        getResult(this.dictionary.getDictionary().keySet());
    }

    public void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        int wordCount = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < wordCount; i++) {
            String word = sc.nextLine();
            String explain = sc.nextLine();
            this.dictionary.addWord(new Word(word, explain));
        }
    }

    public void insertFromFile() {
        Scanner sc;
        try {
            sc = new Scanner(new File(DictionaryCommandline.PATH));
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String word = line.split("\t")[0];
                String explain = line.split("\t")[1];
                this.dictionary.addWord(new Word(word, explain));
            }
            trieNode.buildTrie(this.dictionary.getDictionary().keySet());
        } catch (FileNotFoundException e) {
            System.out.println("Loi: Khong tim thay file dictionaries.txt\n");
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
        else this.dictionary.addWord(word);
    }

    public void dictionarySearchPattern(String pattern) {
        getResult(trieNode.findAllWords(pattern));
    }

    public void dictionaryExportToFile() {
        PrintWriter pw;
        try {
            pw = new PrintWriter(DictionaryCommandline.PATH);
            for (String key : this.dictionary.getDictionary().keySet()) {
                Word word = this.dictionary.getDictionary().get(key);
                pw.printf("%s\t%s\n", word.getWord_target(), word.getWord_explain());
            }
            pw.flush();
            System.out.printf("Xuat file %s thanh cong\n", DictionaryCommandline.PATH);
        } catch (IOException e) {
            System.err.println("\nLoi: Khong ghi duoc file");
        }
    }
}
