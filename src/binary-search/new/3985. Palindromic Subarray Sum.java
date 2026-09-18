// https://leetcode.com/problems/palindromic-subarray-sum
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/binary-serach/new/3985. Palindromic Subarray Sum.java

class Solution {
    private static final long MOD = 1_000_000_007L;
    private static final long BASE = 31L;

    public long getSum(int[] nums) {
        int n = nums.length;

        long[] forwardHash = new long[n + 1];
        buildHash(nums, forwardHash);

        int[] reversedNums = new int[n];
        long[] prefixSum = new long[n + 1];

        for (int i = 0; i < n; i++) {
            reversedNums[n - 1 - i] = nums[i];
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        long[] reverseHash = new long[n + 1];
        buildHash(reversedNums, reverseHash);

        long[] powers = new long[n + 1];
        buildPowers(n, powers);

        long maxSum = 0;
        for (int center = 0; center < n; center++) {

            // Odd length palindrome
            int lo = 0, hi = Math.min(center, n - center - 1);
            int bestOddRadius = 0;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;

                if (isPalindrome(center - mid, center + mid, n, forwardHash, reverseHash, powers)) {
                    bestOddRadius = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }

            int oddLeft = center - bestOddRadius;
            int oddRight = center + bestOddRadius;
            maxSum = Math.max(maxSum, prefixSum[oddRight + 1] - prefixSum[oddLeft]);

            // Even length palindrome
            int maxEvenRadius = Math.min(center, n - center);

            lo = 0;
            hi = maxEvenRadius;
            int bestEvenRadius = 0;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;

                int left = center - mid;
                int right = center + mid - 1;

                if (isPalindrome(left, right, n, forwardHash, reverseHash, powers)) {
                    bestEvenRadius = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }

            if (bestEvenRadius > 0) {
                int evenLeft = center - bestEvenRadius;
                int evenRight = center + bestEvenRadius - 1;

                maxSum = Math.max(maxSum, prefixSum[evenRight + 1] - prefixSum[evenLeft]);
            }
        }

        return maxSum;
    }

    private void buildHash(int[] nums, long[] prefixHash) {
        for (int i = 0; i < nums.length; i++) {
            prefixHash[i + 1] = (prefixHash[i] * BASE + nums[i]) % MOD;
        }
    }

    private long getHash(int left, int right, long[] prefixHash, long[] powers) {
        return (prefixHash[right + 1] - (prefixHash[left] * powers[right - left + 1]) % MOD + MOD) % MOD;
    }

    private boolean isPalindrome(int left, int right, int n, long[] forwardHash, long[] reverseHash, long[] powers) {
        if (left > right) {
            return true;
        }

        long originalHash = getHash(left, right, forwardHash, powers);

        int reverseLeft = n - 1 - right;
        int reverseRight = n - 1 - left;

        long reversedHash = getHash(reverseLeft, reverseRight, reverseHash, powers);

        return originalHash == reversedHash;
    }

    private void buildPowers(int n, long[] powers) {
        powers[0] = 1;

        for (int i = 1; i <= n; i++) {
            powers[i] = (powers[i - 1] * BASE) % MOD;
        }
    }
}