// O(n)
class Solution {
    public int minOperations(int[] nums, int x) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        x = sum - x;
        int n = nums.length, curr = 0, res = -1, j = 0;
        for (int i = 0; i < n; i++) {
            curr += nums[i];
            while (j <= i && curr > x) {
                curr -= nums[j];
                j++;
            }
            if (curr == x) {
                res = Math.max(res, i - j + 1);
            }
        }

        return res == -1 ? -1 : n - res;
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