class Solution {
    public int[] countBits(int n) {
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i / 2] + (i & 1);
        }

        return dp;
    }
}

// 0 - 0     (1 bit)
// 1 - 01    (2 bits)
// 2 - 10    (2 bits)
// 3 - 11    (2 bits)
// 4 - 100   (3 bits)
// 5 - 101   (3 bits)
// 6 - 110   (3 bits)
// 7 - 111   (3 bits)
// 8 - 1000  (4 bits)
// 9 - 1001  (4 bits)
// 10 - 1010 (4 bits)