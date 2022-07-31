class Solution {
    public int[] countBits(int n) {
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i / 2] + (i & 1);
        }

        return dp;
    }
}

//0 - 0
//1 - 01
//2 - 10
//3 - 11
//4 - 100
//5 - 101
//6 - 110
//7 - 111
//8 - 1000
//9 - 1001
//10- 1010