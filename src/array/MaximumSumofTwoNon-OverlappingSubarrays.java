class Solution {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        return Math.max(
                maxSum(nums, firstLen, secondLen),
                maxSum(nums, secondLen, firstLen)
        );
    }

    private int maxSum(int[] nums, int len1, int len2) {
        int n = nums.length;
        int[] prefix = new int[n + 1];

        // Compute prefix sums
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        int maxLen1 = 0, result = 0;

        for (int i = len1 + len2; i <= n; i++) {
            int currLen1 = prefix[i - len2] - prefix[i - len2 - len1];
            maxLen1 = Math.max(maxLen1, currLen1);

            int currLen2 = prefix[i] - prefix[i - len2];
            result = Math.max(result, maxLen1 + currLen2);
        }

        return result;
    }
}

class Solution {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;

        int[] sum1 = new int[n];
        for (int i = 0; i <= n - firstLen; i++) {
            if (i == 0) {
                for (int j = 0; j < firstLen; j++) {
                    sum1[i] += nums[j];
                }
            } else {
                sum1[i] = sum1[i - 1] - nums[i - 1] + nums[i + firstLen - 1];
            }
        }

        int[] sum2 = new int[n];
        for (int i = 0; i <= n - secondLen; i++) {
            if (i == 0) {
                for (int j = 0; j < secondLen; j++) {
                    sum2[i] += nums[j];
                }
            } else {
                sum2[i] = sum2[i - 1] - nums[i - 1] + nums[i + secondLen - 1];
            }
        }

        int[] sum1Max = new int[n + 1];
        int[] sum2Max = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            sum1Max[i] = Math.max(sum1Max[i + 1], sum1[i]);
            sum2Max[i] = Math.max(sum2Max[i + 1], sum2[i]);
        }


        int res = 0;
        for (int i = 0; i <= n - firstLen; i++) {
            res = Math.max(res, sum1[i] + sum2Max[i + firstLen]);
        }
        for (int i = 0; i <= n - secondLen; i++) {
            res = Math.max(res, sum2[i] + sum1Max[i + secondLen]);
        }

        return res;
    }
}