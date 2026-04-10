// n.logn, 1
class Solution {
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0, maxSize = 0;

        for (int right = 0; right < n; right++) {
            // Move left pointer until the condition is satisfied
            while ((long) nums[right] > (long) k * nums[left]) {
                left++;
            }
            // Track the largest valid window size
            maxSize = Math.max(maxSize, right - left + 1);
        }

        return n - maxSize;
    }
}