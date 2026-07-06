// https://leetcode.com/problems/maximum-subarray-sum-after-multiplier
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/array/new/3976. Maximum Subarray Sum After Multiplier.java

class Solution {
    private static final long INF = (long) 1e18;

    public long maxSubarraySum(int[] nums, int k) {
        return Math.max(helper(nums, k, 0), helper(nums, k, 1));
    }

    private long helper(int[] nums, int k, long flag) {
        int n = nums.length;

        long[] dp1 = new long[n];// best window ending at i that has used no transformed element yet (plain Kadane on original values)
        long[] dp2 = new long[n];// best window ending at i that is currently inside the transformed block (element i is transformed)
        long[] dp3 = new long[n];// best window ending at i whose transformed block has already finished (element i is original again, but the window passed through transformed values earlier)

        dp1[0] = nums[0];
        dp2[0] = op(nums[0], k, flag);
        dp3[0] = -INF;// impossible to have already finished a transformed block at index 0, so we forbid it.

        for (int i = 1; i < n; i++) {
            long val = op(nums[i], k, flag);
            dp1[i] = Math.max(nums[i], dp1[i - 1] + nums[i]);
            dp2[i] = Math.max(val, Math.max(dp1[i - 1] + val, dp2[i - 1] + val));
            dp3[i] = Math.max(dp2[i - 1] + nums[i], Math.max(dp3[i - 1] + nums[i], dp2[i]));
        }

        long ans = -INF;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, Math.max(dp2[i], dp3[i]));
        }

        return ans;
    }

    private long op(long x, int k, long flag) {
        if (flag != 0) return x * k;
        return x / k;
    }
}