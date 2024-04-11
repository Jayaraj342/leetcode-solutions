class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        Map<Long, Integer>[] dp = new Map[n];
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
        }
        // dp[idx] key=diff, means # of subseq's ending at idx with diff 'key'
        int res = 0;
        for (int i = 0; i < n; i++) {
            long curr = nums[i];
            for (int j = 0; j < i; j++) {
                long diff = curr - nums[j];
                dp[i].put(diff, 1 + dp[i].getOrDefault(diff, 0) + dp[j].getOrDefault(diff, 0));
                res += dp[j].getOrDefault(diff, 0);
            }
        }

        return res;
    }
}