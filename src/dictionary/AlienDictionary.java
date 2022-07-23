//topological sort
public class Solution {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> adj = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                adj.put(c, new HashSet<>());
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];

            int minLen = Math.min(word1.length(), word2.length());
            int j = 0;
            while (j < minLen) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    adj.get(word1.charAt(j)).add(word2.charAt(j));
                    break;
                }
                j++;
                if (j == minLen && word1.length() > word2.length()) {
                    return "";
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        Map<Character, Boolean> visited = new HashMap<>();

        List<Character> keySet = new ArrayList<>(adj.keySet());
        keySet.sort(Collections.reverseOrder());
        for (char c : keySet) {
            if (dfs(c, sb, adj, visited)) {
                return "";
            }
        }

        return sb.reverse().toString();
    }

    private boolean dfs(char c, StringBuilder sb, Map<Character, Set<Character>> adj, Map<Character, Boolean> visited) {
        if (visited.containsKey(c)) {
            return visited.get(c);
        }

        visited.put(c, true);
        for (char nei : adj.get(c)) {
            if (dfs(nei, sb, adj, visited)) {
                return true;
            }
        }
        visited.put(c, false);
        sb.append(c);
        return false;
    }
}

// ex:
// Input: ["wrt","wrf","er","ett","rftt"]
// Output: "wertf

// adj = {t -> [f], w -> [e], r -> [t], e -> [r]}
// backtrack because many chars can have dependency on each other, but in a loop they can't be dependent