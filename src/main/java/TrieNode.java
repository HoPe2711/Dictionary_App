import java.util.*;

public class TrieNode {
    private Map<Character, TrieNode> map = new HashMap<>();
    private boolean isLeaf = false;
    private String word;
    private TrieNode head;
    private static Set<String> result;

    public TrieNode() {
    }

    public TrieNode(TrieNode head) {
        this.head = head;
    }

    public void insert(String word) {
        TrieNode curr = head;
        for (char c : word.toCharArray()) {
            curr.map.putIfAbsent(c, new TrieNode(head));
            curr = curr.map.get(c);
        }

        curr.isLeaf = true;
        curr.word = word;
    }

    private boolean delete(TrieNode curr, String word, int index) {
        if (index == word.length()) {
            curr.isLeaf = false;
            curr.word = null;
            return curr.map.isEmpty();
        }
        char ch = word.charAt(index);
        TrieNode child = curr.map.get(ch);
        boolean shouldDeleteCurr = delete(child, word, index + 1) && !child.isLeaf;
        if (shouldDeleteCurr) {
            curr.map.remove(ch);
            return curr.map.isEmpty();
        }
        return false;
    }

    public void delete(String word) {
        delete(head, word, 0);
    }

    private void printAllWords(TrieNode root) {
        if (root == null) {
            return;
        }
        if (root.isLeaf) {
            result.add(root.word);
        }
        for (var pair : root.map.entrySet()) {
            TrieNode child = pair.getValue();
            printAllWords(child);
        }
    }

    public void buildTrie(Set<String> dictionary) {
        head = this;
        for (String s : dictionary) insert(s);
    }

    public Set<String> findAllWords(String pattern) {
        TrieNode curr = head;
        result = new TreeSet<>();
        for (char c : pattern.toCharArray()) {
            curr = curr.map.get(c);
            if (curr == null) return result;
        }
        printAllWords(curr);
        return result;
    }
}
