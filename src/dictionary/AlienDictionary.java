// topological sort using BFS (Kahn's algorithm)
class Solution {
    public String alienOrder(String[] words) {
        int len = words.length;
        // get dependencies and count for all chars
        Map<Character, Set<Character>> dependency = new HashMap<>();
        Map<Character, Integer> count = new HashMap<>();
        // init all chars
        for (String word : words) {
            for (char c : word.toCharArray()) {
                count.put(c, 0);
                dependency.put(c, new HashSet<>());
            }
        }
        for (int i = 0; i < len - 1; i++) {
            char[] first = words[i].toCharArray(), second = words[i + 1].toCharArray();
            int m = first.length, n = second.length;
            int k = Math.min(m, n);

            if (m > n && words[i].startsWith(words[i + 1])) {//[abc, ab]
                return "";
            }

            for (int c = 0; c < k; c++) {
                if (first[c] != second[c]) {
                    if (!dependency.get(first[c]).contains(second[c])) {// add only if that edge in not present
                        dependency.get(first[c]).add(second[c]);
                        count.put(second[c], count.get(second[c]) + 1);
                    }
                    break;
                }
            }
        }

        // do BFS and get alienOrder
        Queue<Character> queue = new PriorityQueue<>();
        for (char c : count.keySet()) {
            if (count.get(c) == 0) {
                queue.add(c);
            }
        }

        String res = "";
        while (!queue.isEmpty()) {
            char last = queue.remove();
            res += last;
            for (char nei : dependency.get(last)) {
                count.put(nei, count.get(nei) - 1);
                if (count.get(nei) == 0) {
                    queue.add(nei);
                }
            }
        }

        return res.length() == count.size() ? res : "";// if cycle is present then whole string won't be printed
    }
}

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