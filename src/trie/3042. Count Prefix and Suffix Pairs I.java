class Solution {
    TrieNode root;

    public int countPrefixSuffixPairs(String[] words) {
        root = new TrieNode();

        int n = words.length, res = 0;
        for (int i = n - 1; i >= 0; i--) {
            res += search(words[i]);
            insert(words[i]);
        }

        return res;
    }

    private void insert(String word) {
        TrieNode temp = root;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            char a = word.charAt(i), b = word.charAt(n - i - 1);
            List<Character> pair = List.of(a, b);
            if (!temp.children.containsKey(pair)) {
                temp.children.put(pair, new TrieNode());
            }
            temp = temp.children.get(pair);
            temp.cnt++;
        }
    }

    private int search(String word) {
        TrieNode temp = root;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            char a = word.charAt(i), b = word.charAt(n - i - 1);
            List<Character> pair = List.of(a, b);
            if (!temp.children.containsKey(pair)) {
                temp.children.put(pair, new TrieNode());
            }
            temp = temp.children.get(pair);
        }

        return temp.cnt;
    }

    static class TrieNode {
        Map<List<Character>, TrieNode> children;
        int cnt;

        TrieNode() {
            children = new HashMap<>();
            cnt = 0;
        }
    }

    public static void main(String[] args) {
        new Solution().countPrefixSuffixPairs(new String[]{"a","aba","ababa","aa"});
    }
}

class Solution {
    public int countPrefixSuffixPairs(String[] words) {
        TrieNode root = new TrieNode();
        int res = 0;
        for (String word : words) {
            TrieNode temp = root;
            for (int i = 0, n = word.length(); i < n; ++i) {
                int key = word.charAt(i) * 128 + word.charAt(n - i - 1);
                temp = temp.children.computeIfAbsent(key, (v) -> new TrieNode());
                res += temp.cnt;
            }
            temp.cnt++;
        }

        return res;
    }

    static class TrieNode {
        HashMap<Integer, TrieNode> children = new HashMap<>();
        int cnt = 0;
    }
}