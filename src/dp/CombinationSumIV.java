class Solution {
    Map<String, Integer> memo;

    public int combinationSum4(int[] nums, int target) {
        memo = new HashMap<>();
        return dfs(nums, target, 0);
    }

    private int dfs(int[] nums, int target, int start) {
        if (target == 0) {
            return 1;
        }
        if (target < 0) {
            return 0;
        }

        String key = target + "," + start;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int count = 0;
        for (int i = start; i < nums.length; i++) {
            count += dfs(nums, target - nums[i], start);
        }
        memo.put(key, count);

        return count;
    }
}

class Solution {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }
}