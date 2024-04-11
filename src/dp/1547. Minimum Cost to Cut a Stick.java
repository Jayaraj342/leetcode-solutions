// TC : O(M^3)
// think like palindromes dp version..
class Solution {
    public int minCost(int n, int[] cuts) {
        int m = cuts.length;
        int[] newCuts = new int[m + 2];
        System.arraycopy(cuts, 0, newCuts, 1, m);
        newCuts[m + 1] = n;
        Arrays.sort(newCuts);

        int[][] dp = new int[m + 2][m + 2];
        // 0 -> m+1
        for (int diff = 2; diff < m + 2; diff++) {
            for (int lo = 0; lo < m - diff + 2; lo++) {
                int hi = lo + diff;
                int res = Integer.MAX_VALUE;
                for (int cut = lo + 1; cut < hi; cut++) {
                    res = Math.min(res, newCuts[hi] - newCuts[lo] + dp[lo][cut] + dp[cut][hi]);
                }
                dp[lo][hi] = res;
            }
        }

        return dp[0][m + 1];
    }
}

class Solution {
    Map<String, Integer> memo;

    public int minCost(int n, int[] cuts) {
        memo = new HashMap<>();
        return dfs(cuts, 0, n);
    }

    private int dfs(int[] cuts, int lo, int hi) {
        if (hi - lo == 1) {
            return 0;
        }
        String key = lo + "," + hi;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int res = Integer.MAX_VALUE;
        for (int cut : cuts) {
            if (cut > lo && cut < hi) {
                res = Math.min(res, hi - lo + dfs(cuts, lo, cut) + dfs(cuts, cut, hi));
            }
        }

        res = res == Integer.MAX_VALUE ? 0 : res;
        memo.put(key, res);

        return res;
    }
}