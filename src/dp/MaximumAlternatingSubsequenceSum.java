class Solution {
    Map<String, Long> memo;

    public long maxAlternatingSum(int[] nums) {
        memo = new HashMap<>();
        return dfs(nums, 0, true);
    }

    private long dfs(int[] nums, int idx, boolean isEven) {
        if (idx == nums.length) {
            return 0;
        }
        String key = idx + "," + isEven;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int current = isEven ? nums[idx] : -nums[idx];

        memo.put(key, Math.max(current + dfs(nums, idx + 1, !isEven), dfs(nums, idx + 1, isEven)));
        return memo.get(key);
    }
}