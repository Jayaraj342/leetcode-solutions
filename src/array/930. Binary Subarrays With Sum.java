class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        return helper(nums, goal) - helper(nums, goal - 1);
    }

    // return subarrays with sum <= goal
    private int helper(int[] nums, int goal) {
        if (goal < 0) {
            return 0;
        }
        int lo = 0, sum = 0, res = 0;
        for (int hi = 0; hi < nums.length; hi++) {
            sum += nums[hi];
            while (sum > goal) {
                sum -= nums[lo];
                lo++;
            }
            res += (hi - lo + 1);
        }

        return res;
    }
}