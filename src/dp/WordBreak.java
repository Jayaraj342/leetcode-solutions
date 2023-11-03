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