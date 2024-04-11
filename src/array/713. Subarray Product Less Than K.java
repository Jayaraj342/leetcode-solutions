class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int res = 0, l = 0, prd = 1;
        for (int r = 0; r < nums.length; r++) {
            prd *= nums[r];
            while (prd >= k && l <= r) {
                prd /= nums[l];
                l++;
            }
            res += r - l + 1;
        }

        return res;
    }
}