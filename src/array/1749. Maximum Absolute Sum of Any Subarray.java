class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int prefixSum = 0;
        int maxPrefixSum = 0, minPrefixSum = 0;
        int maxAbsSum = 0;

        for (int num : nums) {
            prefixSum += num;
            maxAbsSum = Math.max(maxAbsSum, Math.max(prefixSum - minPrefixSum, maxPrefixSum - prefixSum));
            maxPrefixSum = Math.max(maxPrefixSum, prefixSum);
            minPrefixSum = Math.min(minPrefixSum, prefixSum);
        }

        return maxAbsSum;
    }
}
