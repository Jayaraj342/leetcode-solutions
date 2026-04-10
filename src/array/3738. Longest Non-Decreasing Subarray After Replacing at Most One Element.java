class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;

        // find the longest subarray starting and ending at any i
        int[] start = new int[n], end = new int[n];
        Arrays.fill(start, 1);
        Arrays.fill(end, 1);

        for (int i = 1; i < n; i++) {
            if (nums[i] >= nums[i - 1]) {
                end[i] += end[i - 1];
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] <= nums[i + 1]) {
                start[i] += start[i + 1];
            }
        }

        int max = Math.min(n, Arrays.stream(start).max().orElse(0) + 1);// Add 1 as we can change max 1 num
        for (int i = 1; i < n - 1; i++) {
            // combine
            if (nums[i - 1] <= nums[i + 1]) {
                max = Math.max(max, end[i - 1] + 1 + start[i + 1]);
            }
        }

        return max;
    }
}