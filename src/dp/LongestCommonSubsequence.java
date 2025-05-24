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

// To find string
//StringBuilder lcs = new StringBuilder();
//int i = m, j = n;
//while (i > 0 && j > 0) {
//    if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
//        lcs.append(str1.charAt(i - 1));
//        i--;
//        j--;
//    } else if (dp[i - 1][j] > dp[i][j - 1]) {
//        i--;
//    } else {
//        j--;
//    }
//}

class Solution {
    Integer[][] memo;

    public int longestCommonSubsequence(String text1, String text2) {
        memo = new Integer[text1.length()][text2.length()];
        return dfs(text1, text2, 0, 0);
    }

    private int dfs(String text1, String text2, int i, int j) {
        if (i == text1.length() || j == text2.length()) {
            return 0;
        }

        if (memo[i][j] != null) {
            return memo[i][j];
        }

        int res;
        if (text1.charAt(i) == text2.charAt(j)) {
            res = 1 + dfs(text1, text2, i + 1, j + 1);
        } else {
            res = Math.max(dfs(text1, text2, i + 1, j), dfs(text1, text2, i, j + 1));
        }
        memo[i][j] = res;
        return res;
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
