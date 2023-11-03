class Solution {
    Map<String, Boolean> memo;

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 == 1) {
            return false;
        }

        memo = new HashMap<>();
        return dfs(nums, 0, sum / 2);
    }

    private boolean dfs(int[] nums, int idx, int target) {
        if (target == 0) {
            return true;
        }
        if (idx == nums.length || target < 0) {
            return false;
        }

        String key = idx + "," + target;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        memo.put(key, dfs(nums, idx + 1, target - nums[idx]) || dfs(nums, idx + 1, target));
        return memo.get(key);
    }
}

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }

        Set<Integer> dp = new HashSet<>();
        dp.add(0);

        for (int num : nums) {
            Set<Integer> temp = new HashSet<>();
            for (int comb : dp) {
                temp.add(comb);
                temp.add(comb + num);
                if (temp.contains(sum / 2)) {
                    return true;
                }
            }
            dp = temp;
        }

        return false;
    }
}