// N * L * 26, N * L
// TLE..
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);

        List<List<String>> res = new ArrayList<>();
        if (!dict.contains(endWord)) {
            return res;
        }

        // Step 1: BFS to build distance map and an adjacency list
        Map<String, Integer> distance = new HashMap<>();
        Map<String, List<String>> adj = new HashMap<>();
        bfs(beginWord, endWord, dict, distance, adj);

        // Step 2: DFS to find all shortest paths
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        dfs(beginWord, endWord, distance, adj, path, res);

        return res;
    }

    private void bfs(String beginWord, String endWord, Set<String> dict,
                     Map<String, Integer> distance, Map<String, List<String>> adj) {
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        distance.put(beginWord, 0);

        for (String word : dict) {
            adj.put(word, new ArrayList<>());
        }
        adj.put(beginWord, new ArrayList<>());

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean foundEnd = false;

            for (int i = 0; i < size; i++) {
                String curr = queue.remove();
                int currDist = distance.get(curr);

                for (String next : getNeighbors(curr, dict)) {
                    adj.get(curr).add(next);

                    // First time visiting next word → set distance and push to queue
                    if (!distance.containsKey(next)) {
                        distance.put(next, currDist + 1);
                        if (next.equals(endWord)) {
                            foundEnd = true;
                        } else {
                            queue.offer(next);
                        }
                    }
                }
            }

            if (foundEnd) break; // Stop BFS at the shortest level
        }
    }

    // backtrack
    private void dfs(String word, String endWord,
                     Map<String, Integer> distance,
                     Map<String, List<String>> adj,
                     List<String> path,
                     List<List<String>> result) {
        if (word.equals(endWord)) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (String next : adj.get(word)) {
            // Only follow the shortest paths
            if (distance.get(next) == distance.get(word) + 1) {
                path.add(next);
                dfs(next, endWord, distance, adj, path, result);
                path.remove(path.size() - 1);
            }
        }
    }

    // Generate neighbors by changing one letter at a time
    private List<String> getNeighbors(String word, Set<String> dict) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char oldChar = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == oldChar) continue;
                chars[i] = c;
                String newWord = new String(chars);
                if (dict.contains(newWord)) {
                    neighbors.add(newWord);
                }
            }
            chars[i] = oldChar;
        }

        return neighbors;
    }
}