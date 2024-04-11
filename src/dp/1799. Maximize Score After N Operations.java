// TC : O(2^n * n^2)
class Solution {
    int[][] gcd;

    public int maxScore(int[] nums) {
        int n = nums.length;
        int[] dp = new int[1 << n];
        gcd = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                gcd[i][j] = getGCD(nums[i], nums[j]);
            }
        }

        // bitmasks `0000 0000 0000 00` means all positions are available (0 nums are used), now if we use 2 bits
        // what's best score we can receive
        for (int bm = 0; bm < (1 << n); bm++) {
            int noBits = Integer.bitCount(bm);
            if (noBits % 2 == 0) {
                for (int i = 0; i < n; i++) {
                    for (int j = i + 1; j < n; j++) {
                        if (
                                ((bm >> i) & 1) == 1 || ((bm >> j) & 1) == 1
                        ) {
                            continue;
                        }
                        int op = (noBits / 2) + 1, newMask = bm | 1 << i | 1 << j;
                        dp[newMask] = Math.max(dp[newMask], dp[bm] + op * gcd[i][j]);
                    }
                }
            }
        }

        return dp[(1 << n) - 1];
    }

    private int getGCD(int a, int b) {
        if (b > a) {
            return getGCD(b, a);
        }
        if (b == 0) {
            return a;
        }

        return getGCD(b, a % b);
    }
}

class Solution {
    int[] memo;
    int[][] gcd;

    public int maxScore(int[] nums) {
        int n = nums.length;
        memo = new int[1 << n];
        gcd = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                gcd[i][j] = getGCD(nums[i], nums[j]);
            }
        }
        return dfs(n, 0, 1);
    }

    // 0000 0000 0000 00 in bitMask means, if none of nums are used what's max score we can receive.
    private int dfs(int n, int bitMask, int op) {
        if (memo[bitMask] != 0) {
            return memo[bitMask];
        }

        memo[bitMask] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (
                        ((bitMask >> i) & 1) == 1 || ((bitMask >> j) & 1) == 1
                ) {
                    continue;
                }
                int newMask = bitMask | 1 << i | 1 << j;
                memo[bitMask] = Math.max(memo[bitMask], op * gcd[i][j] + dfs(n, newMask, op + 1));
            }
        }

        return memo[bitMask];
    }

    private int getGCD(int a, int b) {
        if (b > a) {
            return getGCD(b, a);
        }
        if (b == 0) {
            return a;
        }

        return getGCD(b, a % b);
    }
}