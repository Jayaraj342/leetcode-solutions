// O(log(n)) => with 2 methods..
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

// O(log(n))
class Solution {
    public int[] searchRange(int[] nums, int target) {
        return new int[]{getIndexOf(nums, target, true), getIndexOf(nums, target, false)};
    }

    private int getIndexOf(int[] nums, int target, boolean isLeft) {
        int i = 0, j = nums.length - 1;
        int idx = -1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] < target) {
                i = mid + 1;
            } else if (nums[mid] > target) {
                j = mid - 1;
            }
            if (nums[mid] == target) {
                idx = mid;
                if (isLeft) {
                    j = mid - 1;
                } else {
                    i = mid + 1;
                }
            }
        }

        return idx;
    }
}
