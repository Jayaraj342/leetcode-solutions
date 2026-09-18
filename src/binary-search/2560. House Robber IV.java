class Solution {
    public int minCapability(int[] nums, int k) {
        int lo = nums[0], hi = nums[0];

        for (int num : nums) {
            lo = Math.min(lo, num);
            hi = Math.max(hi, num);
        }

        int res = hi;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (canRobK(nums, k, mid)) {
                res = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return res;
    }

    private boolean canRobK(int[] nums, int k, int maxVal) {
        int count = 0, i = 0;
        while (i < nums.length) {
            if (nums[i] <= maxVal) {
                count++;
                i += 2;  // Skip next house to avoid adjacent robberies
            } else {
                i++;
            }
            if (count >= k) return true;
        }

        return false;
    }
}