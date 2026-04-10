class Solution {
    public int countCoprime(int[][] mat) {
        int MOD = 1_000_000_007;
        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        for (int[] row: mat) {
            Map<Integer, Integer> nxt = new HashMap<>();
            for (int a : row) {
                for (Map.Entry<Integer, Integer> e : cnt.entrySet()) {
                    int g = gcd(a, e.getKey());
                    nxt.put(g, (nxt.getOrDefault(g, 0) + e.getValue()) % MOD);
                }
            }
            cnt = nxt;
        }

        return cnt.getOrDefault(1, 0) % MOD;
    }

    private int gcd(int A, int B) {
        if (A < B) {
            return gcd(B, A);
        }
        if (B == 0) {
            return A;
        }

        return gcd(B, A % B);
    }
}