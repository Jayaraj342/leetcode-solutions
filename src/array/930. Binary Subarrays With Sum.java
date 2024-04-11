class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        return helper(nums, goal) - helper(nums, goal - 1);
    }

    // return subarrays with sum <= goal
    private int helper(int[] nums, int goal) {
        if (goal < 0) {
            return 0;
        }
        int i = 0, sum = 0, res = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            while (sum > goal) {
                sum -= nums[i];
                i++;
            }
            res += (j - i + 1);
        }

        return res;
    }
}