// O(n)
class Solution {
    public int minOperations(int[] nums, int x) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        x = sum - x;
        int i = 0, j = 0, curr = 0;
        int max = -1;
        while (j < nums.length) {
            curr += nums[j];
            while (i <= j && curr > x) {
                curr -= nums[i++];
            }
            if (curr == x) {
                max = Math.max(max, j - i + 1);
            }
            j++;
        }

        return max == -1 ? -1 : nums.length - max;
    }
}

// Timeout
// O(n^3)
class Solution {
    public int minOperations(int[] nums, int x) {
        return dfs(nums, x, 0, nums.length - 1);
    }

    private int dfs(int[] nums, int x, int lo, int hi) {
        if (x == 0) {
            return 0;
        }
        if (lo > hi || x < 0) {
            return -1;
        }

        int useFirst = dfs(nums, x - nums[lo], lo + 1, hi);
        int useLast = dfs(nums, x - nums[hi], lo, hi - 1);

        if(useFirst == -1 && useLast == -1) {
            return -1;
        }
        if (useFirst == -1) {
            return 1 + useLast;
        }
        if (useLast == -1) {
            return 1 + useFirst;
        }

        return 1 + Math.min(useFirst, useLast);
    }
}