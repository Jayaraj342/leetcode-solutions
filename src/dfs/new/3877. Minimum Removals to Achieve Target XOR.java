// https://leetcode.com/problems/minimum-removals-to-achieve-target-xor/
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/dfs/new/3877. Minimum Removals to Achieve Target XOR.java

// n * 10^4
// If nums[i] can have max 10^4 elements, XOR ranges will be around max value only
class Solution {
    Map<String, Integer> memo;

    public int minRemovals(int[] nums, int target) {
        memo = new HashMap<>();

        int res = dfs(nums, target, 0, 0);
        return res >= 0 ? nums.length - res : -1;
    }

    private int dfs(int[] nums, int target, int idx, int xor) {
        if (idx == nums.length) {
            return xor == target ? 0 : -50;
        }
        String key = idx + "," + xor;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int with = 1 + dfs(nums, target, idx + 1, xor ^ nums[idx]);
        int without = dfs(nums, target, idx + 1, xor);

        memo.put(key, Math.max(with, without));
        return memo.get(key);
    }
}

class Solution {
    public int minRemovals(int[] nums, int target) {
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 0);
        for (int num : nums) {
            target ^= num;
            for (Map.Entry<Integer, Integer> entry : dp.entrySet()) {
                int xor = entry.getKey(), cnt = entry.getValue();
                int nextV = xor ^ num;
                if (cnt + 1 < dp.getOrDefault(nextV, 50)) {
                    dp.put(nextV, cnt + 1);
                }
            }
        }

        return dp.getOrDefault(target, -1);
    }
}