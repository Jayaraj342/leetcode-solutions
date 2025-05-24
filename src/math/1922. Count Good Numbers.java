class Solution {
    private static final int MOD = 1_000_000_007;

    public int countGoodNumbers(long n) {
        long evenCount = (n + 1) / 2; // positions where we can place 0,2,4,6,8 (5 choices)
        long oddCount = n / 2;        // positions where we can place prime digits (2,3,5,7) (4 choices)

        long evenWays = modPow(5, evenCount);
        long oddWays = modPow(4, oddCount);

        return (int) ((evenWays * oddWays) % MOD);
    }

    private long modPow(long base, long exp) {
        long result = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }

        return result;
    }
}
