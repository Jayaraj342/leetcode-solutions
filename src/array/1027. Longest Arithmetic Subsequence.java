// TC : O(n^2) SC : O(n^2)
class Solution {
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length, max = 2;
        Map<Integer, Integer>[] dp = new HashMap[n];// i -> [diff, longest sequence]
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int currDiff = nums[j] - nums[i];
                dp[i].put(currDiff, dp[j].getOrDefault(currDiff, 1) + 1);

                max = Math.max(max, dp[i].get(currDiff));
            }
        }

        return max;
    }
}