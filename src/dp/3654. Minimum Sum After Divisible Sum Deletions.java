class Solution {
    public long minArraySum(int[] nums, int k) {
        int n = nums.length;
        long total = 0;
        for (int num : nums) total += num;

        long[] dp = new long[n + 1];
        Map<Integer, Long> best = new HashMap<>();
        best.put(0, 0L); // remainder 0 -> dp[0] - prefix[0]

        long prefix = 0;
        for (int i = 1; i <= n; i++) {
            prefix += nums[i - 1];
            int rem = (int) (prefix % k);

            // O/ skip
            dp[i] = dp[i - 1];

            // 1/ remove a subarray ending at i-1
            if (best.containsKey(rem)) {
                long candidate = best.get(rem) + prefix;
                dp[i] = Math.max(dp[i], candidate);
            }

            long val = dp[i] - prefix;
            best.put(rem, Math.max(best.getOrDefault(rem, Long.MIN_VALUE), val));
        }

        return total - dp[n];
    }
}

class Solution {
    public long minArraySum(int[] nums, int k) {
        long total = 0;
        for (int num : nums) total += num;

        // Prefix sum array
        long sum = 0;

        // Map: mod -> best (dp - sum)
        Map<Integer, Long> best = new HashMap<>();
        best.put(0, 0L);

        long dp = 0; // maximum deletable sum so far

        for (int num : nums) {
            sum += num;
            int mod = (int) (sum % k);

            // If we've seen this modulo before, we can form a divisible subarray
            if (best.containsKey(mod)) {
                dp = Math.max(dp, sum + best.get(mod));
            }

            // Update best for this mod if better
            best.put(mod, Math.max(best.getOrDefault(mod, Long.MIN_VALUE), dp - sum));
        }

        // Remaining sum = total - max deletable sum
        return total - dp;
    }

    public static void main(String[] args) {
        new Solution().minArraySum(new int[]{5, 3, 1, 8}, 8);
    }
}