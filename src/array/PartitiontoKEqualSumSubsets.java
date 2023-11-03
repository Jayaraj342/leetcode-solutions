// O(2^(k*n))
// O(k*n)
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int n = nums.length;
        if (sum % k != 0) {
            return false;
        }
        int avg = sum / k;
        Arrays.sort(nums);

        return dfs(nums, new boolean[n], k, 0, avg, 0);
    }

    private boolean dfs(int[] nums, boolean[] visited, int k, int currentSum, int target, int start) {
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