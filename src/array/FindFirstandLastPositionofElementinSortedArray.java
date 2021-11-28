class Solution {
    public int[] searchRange(int[] nums, int target) {
        return new int[]{getLeftIndex(nums, target), getRightIndex(nums, target)};
    }

    private int getRightIndex(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        int ans = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }

            if (nums[mid] == target) {
                ans = mid;
            }
        }

        return ans;
    }

    private int getLeftIndex(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        int ans = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }

            if (nums[mid] == target) {
                ans = mid;
            }
        }

        return ans;
    }
}