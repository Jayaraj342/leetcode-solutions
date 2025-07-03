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

class Solution {
    public long maxAlternatingSum(int[] nums) {
        long peak = 0;
        long low = 0;
        long ans = 0;
        int i = 0;

        while (i < nums.length) {
            // find peak
            while (i < nums.length - 1 && nums[i] <= nums[i + 1]) {
                i++;
            }
            peak += nums[i];
            ans = Math.max(ans, peak - low);
            int j = i;
            // find valley
            while (j < nums.length - 1 && nums[j] >= nums[j + 1]) {
                j++;
            }
            if (i != j) { // we fell downward
                low += nums[j];
                i = j;
            }
            ans = Math.max(ans, peak - low);
            i++;
        }

        return ans;
    }
}
