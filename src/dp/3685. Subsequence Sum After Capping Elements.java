class Solution {
    public boolean[] subsequenceSumAfterCapping(int[] nums, int k) {
        Arrays.sort(nums);

        int n = nums.length;
        boolean[] res = new boolean[n];
        boolean[] dp = new boolean[k + 1];
        dp[0] = true;

        int i = 0;
        for (int x = 1; x <= n; x++) {
            // expand
            for (; i < n && nums[i] < x; i++) {
                // update dp -> If we had already got 1, now we can get 3...
                for (int sum = k; sum >= nums[i]; sum--) {
                    dp[sum] = dp[sum] || dp[sum - nums[i]];
                }
            }

            // for remaining elements - spread over 0x, 1x, 2x ... and find if k can be summed
            for (int count = 0; count <= (n - i); count++) {
                int times = count * x;
                if (times > k) {
                    break;
                }

                if (dp[k - times]) {
                    res[x - 1] = true;
                    break;
                }
            }
        }

        return res;
    }
}