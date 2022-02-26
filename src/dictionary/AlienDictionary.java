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
                if (j == minLen && word2.length() > word1.length()) {
                    return "";
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        Map<Character, Boolean> visited = new HashMap<>();
        for (char c : adj.keySet()) {
            if (dfs(c, sb, adj, visited)) {
                return "";
            }
            ;
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