// O(n * 2^n) - doesn't depend on k
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % k != 0) return false;

        int target = sum / k;
        int n = nums.length;
        // Sort descending to process larger numbers first (crucial for pruning)
        Arrays.sort(nums);
        reverse(nums);

        // Memoize based on the bitmask of used elements
        Boolean[] memo = new Boolean[1 << n];
        return backtrack(nums, k, 0, 0, 0, target, memo);
    }

    private boolean backtrack(int[] nums, int k, int currentSum, int start, int mask, int target, Boolean[] memo) {
        // Base case: successfully filled k-1 buckets (last bucket must be valid)
        if (k == 1) return true;

        // If this exact configuration of elements has been tried before
        if (memo[mask] != null) return memo[mask];

        // If current bucket is full, move to the next one, resetting start to 0
        if (currentSum == target) {
            return memo[mask] = backtrack(nums, k - 1, 0, 0, mask, target, memo);
        }

        for (int i = start; i < nums.length; i++) {
            // Check if element is unused and fits in current bucket
            if (((mask >> i) & 1) == 0 && currentSum + nums[i] <= target) {
                // Set the i-th bit to mark it as used
                if (backtrack(nums, k, currentSum + nums[i], i + 1, mask | (1 << i), target, memo)) {
                    return memo[mask] = true;
                }
            }
        }

        return memo[mask] = false;
    }

    private void reverse(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int temp = nums[i];
            nums[i++] = nums[j];
            nums[j--] = temp;
        }
    }
}

// O(k^n) - won't timeout..
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