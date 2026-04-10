class Solution {
    private static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        int size = r - l + 1;
        int[] up = new int[size + 1];
        int[] down = new int[size + 1];

        // Base case: For length = 2 (zigzag of size 2)
        for (int i = 1; i <= size; i++) {
            up[i] = i - 1;                  // smaller numbers before i
            down[i] = size - i;             // larger numbers after i
        }

        // For longer zigzags (length >= 3)
        for (int len = 3; len <= n; len++) {
            int[] newUp = new int[size + 1];
            int[] newDown = new int[size + 1];

            // Compute newUp[i]: sum of down[1..i-1]
            int prefix = 0;
            for (int i = 1; i <= size; i++) {
                newUp[i] = prefix;
                prefix = (prefix + down[i]) % MOD;
            }

            // Compute newDown[i]: sum of up[i+1..size]
            int suffix = 0;
            for (int i = size; i >= 1; i--) {
                newDown[i] = suffix;
                suffix = (suffix + up[i]) % MOD;
            }

            up = newUp;
            down = newDown;
        }

        // Final result: sum of both directions
        long res = 0;
        for (int i = 1; i <= size; i++) {
            res = (res + up[i] + down[i]) % MOD;
        }

        return (int) res;
    }
}