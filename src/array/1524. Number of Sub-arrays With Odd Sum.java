class Solution {
    public static int MOD = 1000_000_007;

    public int numOfSubarrays(int[] arr) {
        int n = arr.length, prefixSum = 0;
        int oddPrefixSums = arr[0] % 2 != 0 ? 1 : 0;
        long res = oddPrefixSums;
        for (int i = 1; i < n; i++) {
            prefixSum += arr[i];
            if (prefixSum % 2 != 0) {
                res = (res + (i + 1 - oddPrefixSums)) % MOD;
                oddPrefixSums++;
            } else {
                res = (res + oddPrefixSums) % MOD;
            }
        }

        return (int) res;
    }
}

class Solution {
    private static final int MOD = 1_000_000_007;

    public int numOfSubarrays(int[] arr) {
        int n = arr.length;
        int prefixSum = 0;
        int oddPrefixSumCount = 0;
        long result = 0;

        for (int i = 0; i < n; i++) {
            prefixSum += arr[i];

            if (prefixSum % 2 != 0) {
                // If the prefix sum is odd, count previous even prefix sum counts
                int evenPrefixSumCount = i + 1 - oddPrefixSumCount; // +1 because should count even = 1 before idx 0
                result = (result + evenPrefixSumCount) % MOD;
                oddPrefixSumCount++;
            } else {
                // If the prefix sum is even, count previous odd prefix sum counts
                result = (result + oddPrefixSumCount) % MOD;
            }
        }

        return (int) result;
    }
}
