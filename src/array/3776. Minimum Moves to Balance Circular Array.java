class Solution {
    public long minMoves(int[] balance) {
        int neg = -1, n = balance.length;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            if (balance[i] < 0) {
                neg = i;
            }
            sum += balance[i];
        }

        if (neg == -1) {
            return 0;
        }
        if (sum < 0) {
            return -1;
        }

        int i = 1, j = neg;
        long res = 0;
        while (balance[j] < 0) {
            long currNeiSum = balance[(j - i + n) % n] + balance[(j + i) % n];
            res += Math.min(-balance[j], currNeiSum) * i;
            balance[j] += currNeiSum;
            i++;
        }

        return res;
    }
}