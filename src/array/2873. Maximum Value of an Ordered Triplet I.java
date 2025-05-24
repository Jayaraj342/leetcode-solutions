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

// https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-ii/submissions/1595826911/
class Solution {
    // [max, min, max]
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;

        // Precompute max values from the right
        int[] maxRight = new int[n];
        maxRight[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i + 1], nums[i]);
        }

        int maxLeft = nums[0];
        long maxTripletValue = 0;
        // Iterate through the middle element
        for (int i = 1; i < n - 1; i++) {
            maxTripletValue = Math.max(maxTripletValue, (long) (maxLeft - nums[i]) * maxRight[i + 1]);
            maxLeft = Math.max(maxLeft, nums[i]); // Update maxLeft for next iteration
        }

        return maxTripletValue;
    }
}