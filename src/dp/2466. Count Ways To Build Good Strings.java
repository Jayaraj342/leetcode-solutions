// O(n)
class Solution {
    public int countGoodStrings(int low, int high, int zero, int one) {
        int res = 0, MOD = 1000_000_007;
        int[] dp = new int[high + 1];
        dp[0] = 1;
        for (int i = 1; i <= high; i++) {
            if (i >= zero) {
                dp[i] = (dp[i] + dp[i - zero]) % MOD;
            }
            if (i >= one) {
                dp[i] = (dp[i] + dp[i - one]) % MOD;
            }
            if (i >= low) {
                res = (res + dp[i]) % MOD;
            }
        }

        return res;
    }
}

// O(n)
class Solution {
    int low, high, zero, one;
    Map<Integer, Integer> memo;

    public int countGoodStrings(int low, int high, int zero, int one) {
        this.low = low;
        this.high = high;
        this.zero = zero;
        this.one = one;
        memo = new HashMap<>();
        return dfs(0);
    }

    private int dfs(int n) {
        if (n > high) {
            return 0;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int res = n >= low ? 1 : 0;
        res += dfs(n + zero) + dfs(n + one);
        res %= 1000_000_007;
        memo.put(n, res);

        return res;
    }
}