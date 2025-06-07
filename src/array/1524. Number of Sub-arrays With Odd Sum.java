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
