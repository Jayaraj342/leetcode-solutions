// https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-ii/submissions/1595826911/
class Solution {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;

        // suffixMax[i] = maximum value from i to n-1
        int[] suffixMax = new int[n];
        suffixMax[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffixMax[i] = Math.max(nums[i], suffixMax[i + 1]);
        }

        long ans = 0;
        int leftMax = nums[0];

        for (int j = 1; j < n - 1; j++) {
            long value = (leftMax - 1L * nums[j]) * suffixMax[j + 1];
            ans = Math.max(ans, value);

            leftMax = Math.max(leftMax, nums[j]);
        }

        return ans;
    }
}

class Solution {
    // [max, min, max]
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;

        int maxLeft = nums[0];
        long maxTripletValue = 0;

        for (int i = 1; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                maxTripletValue = Math.max(maxTripletValue, (long) (maxLeft - nums[i]) * nums[j]);
            }
            maxLeft = Math.max(maxLeft, nums[i]);
        }

        return maxTripletValue;
    }
}