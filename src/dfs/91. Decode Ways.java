class Solution {
    public int numDecodings(String s) {
        Integer[] memo = new Integer[s.length() + 1];
        return dfs(0, s, memo);
    }

    private int dfs(int start, String s, Integer[] memo) {
        if (memo[start] != null) {
            return memo[start];
        }
        int n = s.length();

        if (start == n) return 1;
        if (s.charAt(start) == '0') return 0;

        int ways = dfs(start + 1, s, memo);
        if (start < n - 1 && (s.charAt(start) == '1' || s.charAt(start) == '2' && s.charAt(start + 1) < '7')) {
            ways += dfs(start + 2, s, memo);
        }

        memo[start] = ways;
        return ways;
    }
}

class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i <= n; i++) {
            String lastOne = s.substring(i - 1, i);// char at i-1
            String lastTwo = s.substring(i - 2, i);// char at i-2 & i-1

            if (Integer.parseInt(lastOne) >= 1) {
                dp[i] += dp[i - 1];
            }

            if (Integer.parseInt(lastTwo) >= 10 && Integer.parseInt(lastTwo) <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }
}