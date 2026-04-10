// n, 1
class Solution {
    private static final int MOD = 1_000_000_007;

    public int countStableSubsequences(int[] nums) {
        long end1Odd = 0, end2Odd = 0;   // subsequences ending with 1 or 2 odds
        long end1Even = 0, end2Even = 0; // subsequences ending with 1 or 2 evens

        for (int num : nums) {
            if ((num & 1) == 1) { // odd
                long newSingle = (end1Even + end2Even + 1) % MOD; // start or continue after evens
                long newDouble = end1Odd;                         // extend single-odd to double-odd
                end1Odd = (end1Odd + newSingle) % MOD;
                end2Odd = (end2Odd + newDouble) % MOD;
            } else { // even
                long newSingle = (end1Odd + end2Odd + 1) % MOD;   // start or continue after odds
                long newDouble = end1Even;                        // extend single-even to double-even
                end1Even = (end1Even + newSingle) % MOD;
                end2Even = (end2Even + newDouble) % MOD;
            }
        }

        long ans = (end1Odd + end2Odd + end1Even + end2Even) % MOD;
        return (int) ans;
    }
}

// TLE : use matrix
class Solution {
    Map<String, Integer> memo;
    int MOD = 1_000_000_007;

    public int countStableSubsequences(int[] nums) {
        memo = new HashMap<>();
        return dfs(nums, null, null, 0) - 1;// subtract empty subsequence
    }

    private int dfs(int[] nums, Boolean prev1, Boolean prev2, int idx) {
        if (idx == nums.length) {
            return 1;
        }

        String key = prev1 + "," + prev2 + "," + idx;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int res = dfs(nums, prev1, prev2, idx + 1); // skip

        if (prev1 == null || prev2 == null) {
            res = (res + dfs(nums, nums[idx] % 2 == 0, prev1, idx + 1)) % MOD;
        } else {
            boolean isEven = nums[idx] % 2 == 0;

            // only add if not forming 3 consecutive same parity
            if (!((isEven && prev1 && prev2) || (!isEven && !prev1 && !prev2))) {
                res = (res + dfs(nums, isEven, prev1, idx + 1)) % MOD;
            }
        }

        memo.put(key, res);
        return res;
    }
}