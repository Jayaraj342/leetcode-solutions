// SC : O(1)
class Solution {
    // for any char previous chars:
    // a : e, i, u
    // e : a, i
    // i : e, o
    // o : i
    // u : i, o
    public int countVowelPermutation(int n) {
        int MOD = 1000_000_007;
        long a = 1, e = 1, i = 1, o = 1, u = 1;

        for (int j = 1; j < n; j++) {
            long aNext = (e + i + u) % MOD;
            long eNext = (a + i) % MOD;
            long iNext = (e + o) % MOD;
            long oNext = (i) % MOD;
            long uNext = (i + o) % MOD;

            a = aNext;
            e = eNext;
            i = iNext;
            o = oNext;
            u = uNext;
        }

        return (int) ((a + e + i + o + u) % MOD);
    }
}

// SC : O(n)
class Solution {
    // for any char previous chars:
    // a : e, i, u
    // e : a, i
    // i : e, o
    // o : i
    // u : i, o
    public int countVowelPermutation(int n) {
        int MOD = 1000_000_007;
        Map<Character, Long>[] dp = new Map[n];
        for (int i = 0; i < n; i++) {
            Map<Character, Long> curr = new HashMap<>();
            curr.put('a', i == 0 ? 1L : 0);
            curr.put('e', i == 0 ? 1L : 0);
            curr.put('i', i == 0 ? 1L : 0);
            curr.put('o', i == 0 ? 1L : 0);
            curr.put('u', i == 0 ? 1L : 0);
            dp[i] = curr;
        }

        for (int i = 1; i < n; i++) {
            dp[i].put('a', (dp[i - 1].get('e') + dp[i - 1].get('i') + dp[i - 1].get('u'))  % MOD);
            dp[i].put('e', (dp[i - 1].get('a') + dp[i - 1].get('i')) % MOD);
            dp[i].put('i', (dp[i - 1].get('e') + dp[i - 1].get('o')) % MOD);
            dp[i].put('o', (dp[i - 1].get('i')) % MOD);
            dp[i].put('u', (dp[i - 1].get('i') + dp[i - 1].get('o')) % MOD);
        }

        long res = 0;
        for (long val : dp[n - 1].values()) {
            res += val;
            res = (res + MOD) % MOD;
        }

        return (int) res;
    }
}