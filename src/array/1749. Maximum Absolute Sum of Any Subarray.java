class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int sumP = 0, sumN = 0;
        int max = 0;
        for (int num : nums) {
            sumP += num;
            if (sumP < 0) {
                sumP = 0;
            }

            sumN += num;
            if (sumN > 0) {
                sumN = 0;
            }

            max = Math.max(max, Math.max(sumP, Math.abs(sumN)));
        }

        return max;
    }
}

class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int maxSum = 0, minSum = 0;
        int currentMax = 0, currentMin = 0;

        for (int num : nums) {
            currentMax = Math.max(num, currentMax + num);
            maxSum = Math.max(maxSum, currentMax);

            currentMin = Math.min(num, currentMin + num);
            minSum = Math.min(minSum, currentMin);
        }

        return Math.max(maxSum, Math.abs(minSum));
    }
}

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
