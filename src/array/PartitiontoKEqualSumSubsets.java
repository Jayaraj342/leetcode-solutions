// O(k^n)
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % k != 0) {
            return false;
        }

        //sort so we can take last element and start filling our bucket
        Arrays.sort(nums);

        return canPartitionKSubsets(nums, sum / k, nums.length - 1, new int[k]);
    }

    public boolean canPartitionKSubsets(int[] nums, int target, int idx, int[] bucket) {
        if (idx == -1) {
            return true;
        }

        for (int j = 0; j < bucket.length; j++) {
            if (bucket[j] + nums[idx] <= target) {
                bucket[j] += nums[idx];
                if (canPartitionKSubsets(nums, target, idx - 1, bucket)) {
                    return true;
                }
                bucket[j] -= nums[idx];
            }

            // If bucket[j] is still 0 after trying to place nums[idx] and failing (via backtracking), that means placing nums[idx] into any other empty bucket will also fail
            if (bucket[j] == 0) {
                break;
            }
        }

        return false;
    }
}

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