class Solution {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int currentSum = 0;
        for (int num: nums) {
            currentSum += num;
            max = Math.max(currentSum, max);
            if(currentSum < 0) {
                currentSum = 0;
            }
        }

        return max;
    }
}