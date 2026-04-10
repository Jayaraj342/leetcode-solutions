// n, 1
class Solution {
    public int[] rotateElements(int[] nums, int k) {
        int n = nums.length;

        int nonNegCount = countNonNegatives(nums);
        if (nonNegCount == 0) return nums;

        k %= nonNegCount;
        if (k == 0) return nums;

        // Reverse all non-negatives
        reverseNonNegatives(nums, 0, n - 1);

        // Find split point
        int seen = 0;
        int splitIndex = -1;

        for (int i = 0; i < n; i++) {
            if (nums[i] >= 0) {
                seen++;
                if (seen == nonNegCount - k) {
                    splitIndex = i;
                    break;
                }
            }
        }

        // Reverse both parts
        reverseNonNegatives(nums, 0, splitIndex);
        reverseNonNegatives(nums, splitIndex + 1, n - 1);

        return nums;
    }

    private int countNonNegatives(int[] nums) {
        int count = 0;
        for (int num : nums) {
            if (num >= 0) count++;
        }
        return count;
    }

    private void reverseNonNegatives(int[] nums, int left, int right) {

        while (left < right) {

            // move left
            while (left < right && nums[left] < 0) {
                left++;
            }

            // move right
            while (left < right && nums[right] < 0) {
                right--;
            }

            if (left < right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
