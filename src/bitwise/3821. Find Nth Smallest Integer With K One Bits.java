class Solution {
    public long nthSmallest(long n, int k) {
        long[][] comb = new long[51][51];
        for (int i = 0; i <= 50; i++) {
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j < i; j++) {
                // with i available positions, how many ways j set bits can be positioned
                comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];
            }
        }

        // From MSB, we'll set a bit, and calculate how many nums are there in remaining positions
        long res = 0;
        for (int i = 50; i >= 0 && k > 0; i--) {
            long cnt = comb[i][k];
            if (n > cnt) {
                res = res | (1L << i);

                n -= cnt;
                k--;
            }
        }

        return res;
    }
}