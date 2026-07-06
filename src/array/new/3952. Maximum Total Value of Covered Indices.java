// https://leetcode.com/problems/maximum-total-value-of-covered-indices
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/array/new/3952. Maximum Total Value of Covered Indices.java

// n, 1
class Solution {
    // Among consecutive one's with a single 0 - should take all except min one
    public long maxTotal(int[] nums, String s) {
        long res = 0;
        int min = Integer.MAX_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            res += nums[i];
            min = Math.min(min, nums[i]);
            if (s.charAt(i) == '0') {
                res -= min;
                min = Integer.MAX_VALUE;
            }
        }

        return res;
    }
}

// n, n
class Solution {
    Map<String, Long> memo;

    public long maxTotal(int[] nums, String s) {
        memo = new HashMap<>();
        return dfs(nums, s, 0, false);
    }

    private long dfs(int[] nums, String s, int idx, boolean lastNumUsed) {
        if (idx == nums.length) {
            return 0;
        }

        String key = idx + "," + lastNumUsed;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (s.charAt(idx) == '0') {
            memo.put(key, dfs(nums, s, idx + 1, false));
            return memo.get(key);
        } else {
            // use curr num
            long res = nums[idx] + dfs(nums, s, idx + 1, true);
            if (!lastNumUsed && idx > 0) {
                res = Math.max(res, nums[idx - 1] + dfs(nums, s, idx + 1, false));
            }

            memo.put(key, res);
            return memo.get(key);
        }
    }
}