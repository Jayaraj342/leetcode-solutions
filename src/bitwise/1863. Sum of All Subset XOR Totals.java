class Solution {
    // If there is 1 is any bit, then that will contribute 1 in half of subsets
    public int subsetXORSum(int[] nums) {
        int n = nums.length, res = 0;
        for (int num : nums) {
            res |= num;
        }

        return res * (int) Math.pow(2, n - 1);
    }
}

class Solution {
    // If there is 1 is any bit, then that will contribute 1 in half of subsets
    public int subsetXORSum(int[] nums) {
        int n = nums.length, res = 0;
        int halfOfSubsets = (int) Math.pow(2, n - 1);
        for (int i = 0; i < 32; i++) {
            int ones = 0;
            for (int num : nums) {
                ones += ((num >> i) & 1);
            }

            if (ones > 0) {
                res += halfOfSubsets * (int) Math.pow(2, i);
            }
        }

        return res;
    }
}

// TC : O(2^n)
class Solution {
    public int subsetXORSum(int[] nums) {
        return dfs(nums, 0, 0);
    }

    private int dfs(int[] nums, int idx, int xor) {
        if (idx == nums.length) {
            return xor;
        }

        return dfs(nums, idx + 1, xor ^ nums[idx]) + dfs(nums, idx + 1, xor);
    }
}