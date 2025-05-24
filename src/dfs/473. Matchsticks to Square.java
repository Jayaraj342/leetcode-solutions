class Solution {
    public boolean makesquare(int[] matchsticks) {
        Arrays.sort(matchsticks);

        long sum = 0;
        for (int num : matchsticks) {
            sum += num;
        }

        long target = sum / 4;
        return sum % 4 == 0 && dfs(matchsticks, new boolean[matchsticks.length], 4, 0, target, 0);
    }

    private boolean dfs(int[] nums, boolean[] visited, int k, int currentSum, long target, int start) {
        if (k == 0) {
            return true;
        }

        if (currentSum == target) {
            return dfs(nums, visited, k - 1, 0, target, 0);
        }

        for (int i = start; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }

            if (i >= 1 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }

            if (currentSum + nums[i] > target) {
                continue;
            }

            visited[i] = true;
            if (dfs(nums, visited, k, currentSum + nums[i], target, i + 1)) {
                return true;
            }
            visited[i] = false;
        }

        return false;
    }
}

// timeout
class Solution {
    public boolean makesquare(int[] matchsticks) {
        Arrays.sort(matchsticks);

        long sum = 0;
        for (int num : matchsticks) {
            sum += num;
        }

        return sum % 4 == 0 && backtrack(matchsticks, new long[4], sum / 4, 0);
    }

    private boolean backtrack(int[] nums, long[] splits, long target, int idx) {
        if (idx == nums.length) {
            return true;
        }

        for (int j = 0; j < 4; j++) {
            if (splits[j] + nums[idx] <= target) {
                splits[j] += nums[idx];
                if (backtrack(nums, splits, target, idx + 1)) {
                    return true;
                }
                splits[j] -= nums[idx];
            }
        }

        return false;
    }
}

// reverse sorting works
class Solution {
    public boolean makesquare(int[] matchsticks) {
        Arrays.sort(matchsticks);
        int n = matchsticks.length;
        for (int i = 0; i < n / 2; i++) {
            int temp = matchsticks[i];
            matchsticks[i] = matchsticks[n - i - 1];
            matchsticks[n - i - 1] = temp;
        }

        long sum = 0;
        for (int num : matchsticks) {
            sum += num;
        }

        return sum % 4 == 0 && backtrack(matchsticks, new long[4], sum / 4, 0);
    }

    private boolean backtrack(int[] nums, long[] splits, long target, int idx) {
        if (idx == nums.length) {
            return true;
            // return sides[0] == target && sides[1] == target &&
            //        sides[2] == target && sides[3] == target;
        }

        for (int j = 0; j < 4; j++) {
            if (splits[j] + nums[idx] <= target) {
                splits[j] += nums[idx];
                if (backtrack(nums, splits, target, idx + 1)) {
                    return true;
                }
                splits[j] -= nums[idx];
            }
        }

        return false;
    }
}