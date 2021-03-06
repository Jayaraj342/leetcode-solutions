class Solution {
    HashMap<String, Boolean> memo = new HashMap<>();
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length() == 0) {
            return s2.equals(s3);
        }
        if(s2.length() == 0) {
            return s1.equals(s3);
        }
        if(s1.length() + s2.length() != s3.length()) {
            return false;
        }
        return dfs(s1, s2, s3, 0, 0);
    }

    private boolean dfs(String s1, String s2, String s3, int i, int j) {
        if (i == s1.length() && j == s2.length()) {
            return true;
        }

        String key = i + "," + j;
        if(memo.containsKey(key)) {
            return memo.get(key);
        }

        if (i < s1.length() && s3.charAt(i + j) == s1.charAt(i) && dfs(s1, s2, s3, i + 1, j)) {
            memo.put(key, true);
            return true;
        }
        if (j < s2.length() && s3.charAt(i + j) == s2.charAt(j) && dfs(s1, s2, s3, i, j + 1)) {
            memo.put(key, true);
            return true;
        }

        memo.put(key, false);
        return false;
    }
}

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() == 0) {
            return s2.equals(s3);
        }
        if (s2.length() == 0) {
            return s1.equals(s3);
        }
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;

        for (int i = 1; i <= s1.length(); i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int j = 1; j <= s2.length(); j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1) || dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
            }
        }

        return dp[s1.length()][s2.length()];
    }
}