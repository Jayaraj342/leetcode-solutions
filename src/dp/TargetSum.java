class Solution {
    Map<String, Integer> memo = new HashMap<>();

    public int findTargetSumWays(int[] nums, int target) {
        return dfs(nums, 0, 0, target);
    }

    public int dfs(int[] nums, int i, int sum, int target) {
        String key = i + "," + sum;
        if (i == nums.length) {
            return sum == target ? 1 : 0;
        }
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int currentWays = dfs(nums, i + 1, sum + nums[i], target) + dfs(nums, i + 1, sum - nums[i], target);
        memo.put(key, currentWays);

        return currentWays;
    }
}