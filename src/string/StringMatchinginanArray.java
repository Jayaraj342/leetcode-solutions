// L * n^2
class Solution {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int count;
    }

    public List<String> stringMatching(String[] words) {
        TrieNode root = new TrieNode();

        // Insert every suffix of every word.
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                insert(root, word, i);
            }
        }

        List<String> result = new ArrayList<>();

        for (String word : words) {
            if (search(root, word)) {
                result.add(word);
            }
        }

        return result;
    }

    private void insert(TrieNode root, String word, int start) {
        TrieNode node = root;

        for (int i = start; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';

            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }

            node = node.children[index];
            node.count++;
        }
    }

    private boolean search(TrieNode root, String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            node = node.children[c - 'a'];

            if (node == null) {
                return false;
            }
        }

        return node.count > 1;
    }
}

// L * n^2 => KMP
class Solution {
    public List<String> stringMatching(String[] words) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            String pattern = words[i];
            int[] lps = computeLPSArray(pattern.toCharArray(), pattern.length());

            for (int j = 0; j < words.length; j++) {
                if (i == j) {
                    continue;
                }

                if (searchPattern(pattern, words[j], lps)) {
                    result.add(pattern);
                    break;
                }
            }
        }

        return result;
    }

    private boolean searchPattern(String pat, String txt, int[] lps) {
        int m = pat.length();
        int n = txt.length();

        int j = 0;
        int i = 0;

        while (i < n) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }

            if (j == m) {
                return true;
            }

            // mismatch after j matches
            else if (i < n && pat.charAt(j) != txt.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return false;
    }

    private int[] computeLPSArray(char[] pat, int m) {
        int[] lps = new int[m];

        int len = 0;
        int i = 1; // lps[0] is always 0

        while (i < m) {
            if (pat[i] == pat[len]) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
}

// n.L^2 OR n^2.L
class Solution {
    public List<String> stringMatching(String[] words) {
        String sum = String.join(" ", words);
        List<String> list = new ArrayList<>();
        for (String word : words) {
            if (sum.indexOf(word) != sum.lastIndexOf(word)) {
                list.add(word);
            }
        }

        return list;
    }
}

