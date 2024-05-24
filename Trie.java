import java.util.*;

public class Trie {
    public class TrieNode {
        public boolean isEnd;
        public Map<Character, TrieNode> children = new HashMap<>();

        public TrieNode() {
            isEnd = false;
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.children.containsKey(ch)) {
                node.children.put(ch, new TrieNode());
            }
            node = node.children.get(ch);
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.children.containsKey(ch)) {
                return false;
            }
            node = node.children.get(ch);
        }
        return node.isEnd;
    }

    public String replaceSensitiveWords(String text, String replacement) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int j = i, k = i;
            TrieNode node = root;
            while (j < text.length() && node.children.containsKey(text.charAt(j))) {
                node = node.children.get(text.charAt(j));
                if (node.isEnd) {
                    k = j;
                }
                j++;
            }
            if (k != i) {
                result.append(replacement.repeat(k - i + 1));
                i = k;
            } else {
                result.append(text.charAt(i));
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("banana");
        trie.insert("orange");
        trie.insert("苹果");
        trie.insert("水果果");

        System.out.println("hint apple:" + trie.search("apple"));
        System.out.println("hint banana:" + trie.search("banana"));
        System.out.println("hint orange:" + trie.search("orange"));
        System.out.println("hint grape:" + trie.search("grape"));

        System.out.println(trie.replaceSensitiveWords("apple is a fruit", "*"));
        System.out.println(trie.replaceSensitiveWords("banana is a fruit", "*"));
        System.out.println(trie.replaceSensitiveWords("orange is a fruit", "*"));
        System.out.println(trie.replaceSensitiveWords("苹果就是一种苹果的水果", "*"));
        System.out.println(trie.replaceSensitiveWords("grape is a fruit", "*"));
    }
}