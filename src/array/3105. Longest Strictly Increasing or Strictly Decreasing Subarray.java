class Solution {
    // not the sub sequence (no stack usage)
    public int longestMonotonicSubarray(int[] nums) {
        int increasing = 1, decreasing = 1, res = 1;
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                increasing++;
            } else {
                increasing = 1;
            }

            if (nums[i] < nums[i - 1]) {
                decreasing++;
            } else {
                decreasing = 1;
            }

            int curr = Math.max(increasing, decreasing);
            res = Math.max(res, curr);
        }

        return res;
    }
}