class Solution {
    Map<String, Integer> memo = new HashMap<>();

    public int longestCommonSubsequence(String text1, String text2) {
        return dfs(text1, text2, 0, 0);
    }

    private int dfs(String text1, String text2, int i, int j) {
        String currentKey = i + "," + j;
        if (memo.containsKey(currentKey)) {
            return memo.get(currentKey);
        }
        if (i < text1.length() && j < text2.length()) {
            if (text1.charAt(i) == text2.charAt(j)) {
                memo.put(currentKey, 1 + dfs(text1, text2, i + 1, j + 1));
                return memo.get(currentKey);
            } else {
                memo.put(currentKey, 1 + Math.max(dfs(text1, text2, i, j + 1), dfs(text1, text2, i + 1, j)));
                return memo.get(currentKey);
            }
        }

        return 0;
    }
}

class Solution {
    Map<String, Integer> memo = new HashMap<>();

    public int longestCommonSubsequence(String text1, String text2) {
        return dfs(text1, text2, text1.length(), text2.length());
    }

    private int dfs(String text1, String text2, int i, int j) {
        if(i == 0 || j == 0) {
            return 0;
        }

        String currentKey = i + "," + j;
        if (memo.containsKey(currentKey)) {
            return memo.get(currentKey);
        }

        if (text1.charAt(i-1) == text2.charAt(j-1)) {
            memo.put(currentKey, 1 + dfs(text1, text2, i - 1, j - 1));
            return memo.get(currentKey);
        } else {
            memo.put(currentKey, Math.max(dfs(text1, text2, i, j - 1), dfs(text1, text2, i - 1, j)));
            return memo.get(currentKey);
        }
    }
}

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = text1.length() - 1; i >= 0; i--) {
            for (int j = text2.length() - 1; j >= 0; j--) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        return dp[0][0];
    }
}
