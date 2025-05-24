class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int max = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (nums[j] % nums[i] == 0) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            max = Math.max(max, dp[i]);
        }

        List<Integer> res = new ArrayList<>();
        int last = 1;
        for (int i = 0; i < n; i++) {
            if (dp[i] == max && nums[i] % last == 0) {
                res.add(nums[i]);
                max--;
                last = nums[i];
            }
        }

        return res;
    }
}

class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n];         // dp[i] = size of the largest subset ending with nums[i]
        int[] prev = new int[n];       // prev[i] = index of previous element in the subset

        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);

        int maxIdx = 0;  // Index of the largest value in dp

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            if (dp[i] > dp[maxIdx]) {
                maxIdx = i;
            }
        }

        // Reconstruct the subset
        List<Integer> res = new ArrayList<>();
        while (maxIdx >= 0) {
            res.add(nums[maxIdx]);
            maxIdx = prev[maxIdx];
        }

        Collections.reverse(res);
        return res;
    }
}

class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);
        int max = 0, endIdx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            if (dp[i] > max) {
                max = dp[i];
                endIdx = i;
            }
        }

        List<Integer> res = new ArrayList<>();
        int prevNum = nums[endIdx--];
        res.add(prevNum);
        max--;
        while (endIdx >= 0) {
            if (prevNum % nums[endIdx] == 0 && dp[endIdx] == max) {
                res.add(nums[endIdx]);
                prevNum = nums[endIdx];
                max--;
            }
            endIdx--;
        }

        return res;
    }
}

class Solution {
    Map<Integer, List<Integer>> memo;

    public List<Integer> largestDivisibleSubset(int[] nums) {
        memo = new HashMap<>();
        Arrays.sort(nums);

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> curr = dfs(nums, i);
            if (curr.size() > res.size()) {
                res = curr;
            }
        }
        return res;
    }

    private List<Integer> dfs(int[] nums, int i) {
        if (i == nums.length) {
            return new ArrayList<>();
        }
        if (memo.containsKey(i)) {
            return memo.get(i);
        }

        List<Integer> res = new ArrayList<>();
        res.add(nums[i]);
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] % nums[i] == 0) {
                // won't be O(n) as 2 ^ whatever, can be done only max 32 times, it doesn't depend on n
                List<Integer> temp = new ArrayList<>(dfs(nums, j));
                temp.add(nums[i]);
                if (temp.size() > res.size()) {
                    res = temp;
                }
            }
        }
        memo.put(i, res);

        return res;
    }
}