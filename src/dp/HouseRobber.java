class Solution {
    Integer[] memo;

    public int rob(int[] nums) {
        memo = new Integer[nums.length];
        return dfs(nums, 0);
    }

    private int dfs(int[] nums, int start) {
        if (start >= nums.length) {
            return 0;
        }
        if (memo[start] != null) {
            return memo[start];
        }

        memo[start] = Math.max(nums[start] + dfs(nums, start + 2), dfs(nums, start + 1));
        return memo[start];
    }
}

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 3];

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = nums[i] + Math.max(dp[i + 2], dp[i + 3]);
        }

        return Math.max(dp[0], dp[1]);
    }
}

class Solution {
    public int rob(int[] nums) {
        int rob1 = 0;
        int rob2 = 0;
        // [rob1, rob2, n, n+1...]
        for (int n : nums) {
            int temp = Math.max(n + rob1, rob2);
            rob1 = rob2;
            rob2 = temp;
        }

        return rob2;
    }
}

// House Robber 11
class Solution {
    Integer[] memo;

    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 1) {
            return nums[0];
        }
        memo = new Integer[n];
        int withLast = dfs(nums, 1, n);
        
        memo = new Integer[n];
        int withFirst = dfs(nums, 0, n - 1);
        
        return Math.max(withLast, withFirst);
    }

    private int dfs(int[] nums, int start, int n) {
        if (start >= n) {
            return 0;
        }
        if (memo[start] != null) {
            return memo[start];
        }

        memo[start] = Math.max(nums[start] + dfs(nums, start + 2, n), dfs(nums, start + 1, n));
        return memo[start];
    }
}
