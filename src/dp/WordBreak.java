// n * m * k, n
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[s.length()] = true;

        for (int i = s.length() - 1; i >= 0; i--) {
            for (String word : wordDict) {
                if (i + word.length() <= s.length() && allCharsMatch(s, i, word)){
                    dp[i] = dp[i + word.length()];
                }
                if (dp[i]) {
                    break;
                }
            }
        }

        return dp[0];
    }

    private boolean allCharsMatch(String str, int i, String word) {
        int j = 0;
        while (j < word.length() && word.charAt(j) == str.charAt(i)) {
            i++;
            j++;
        }

        return j == word.length();
    }
}

// TC : O(n^2) SC : O(n + m)
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        return dfs(s, new HashSet<>(wordDict), 0);
    }

    Map<Integer, Boolean> memo = new HashMap<>();

    private boolean dfs(String s, Set<String> dict, int start) {
        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        int n = s.length();
        if (start == n) {
            return true;
        }

        for (int i = start + 1; i <= n; i++) {
            if (dict.contains(s.substring(start, i)) && dfs(s, dict, i)) {
                return true;
            }
        }
        memo.put(start, false);

        return false;
    }
}

// ---------------------------------------------------------------------------------------------------------------------
// https://leetcode.com/problems/word-break-ii/
// 2^n * n
class Solution {
    List<String> res;

    public List<String> wordBreak(String s, List<String> wordDict) {
        res = new ArrayList<>();
        dfs(s, wordDict, 0, new ArrayList<>());

        return res;
    }

    private void dfs(String s, List<String> wordDict, int idx, List<String> curr) {
        int n = s.length();
        if (idx == n) {
            StringBuilder sb = new StringBuilder();
            for (String word : curr) {
                sb.append(word).append(" ");
            }
            res.add(sb.toString().trim());
            return;
        }

        for (String word : wordDict) {
            int nIdx = idx + word.length();
            if (nIdx <= n) {
                String cut = s.substring(idx, nIdx);
                if (word.equals(cut)) {
                    curr.add(word);
                    dfs(s, wordDict, nIdx, curr);
                    curr.remove(curr.size() - 1);
                }
            }
        }
    }
}

// 2^n * n
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        return dfs(s, new HashSet<>(wordDict), new HashMap<String, LinkedList<String>>());
    }

    // dfs function returns an array including all substrings derived from s.
    List<String> dfs(String s, Set<String> wordDict, HashMap<String, LinkedList<String>> map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }

        LinkedList<String> res = new LinkedList<>();
        if (s.isEmpty()) {
            res.add("");
            return res;
        }

        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> sublist = dfs(s.substring(word.length()), wordDict, map);
                for (String sub : sublist) {
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
        }
        map.put(s, res);

        return res;
    }
}