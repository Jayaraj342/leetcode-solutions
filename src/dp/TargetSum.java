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

class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        Map<Integer, Integer> counter = new HashMap<>();
        counter.put(0, 1);

        for (int num : nums) {
            Map<Integer, Integer> temp = new HashMap<>();

            for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
                int total = entry.getKey();
                int count = entry.getValue();

                temp.put(total + num, temp.getOrDefault(total + num, 0) + count);
                temp.put(total - num, temp.getOrDefault(total - num, 0) + count);
            }
            counter = temp;
        }

        return counter.getOrDefault(target, 0);
    }
}