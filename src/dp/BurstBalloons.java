// not this
class Solution {
    int max = 0;

    public int maxCoins(int[] nums) {
        backtrack(nums, new HashSet<>(), 0);
        return max;
    }

    private void backtrack(int[] nums, Set<Integer> used, int product) {
        if (used.size() == nums.length) {
            max = Math.max(max, product);
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (!used.contains(i)) {
                    used.add(i);
                    backtrack(nums, used, product + neighborProduct(used, i, nums));
                    used.remove(i);
                }
            }
        }
    }

    private int neighborProduct(Set<Integer> used, int i, int[] nums) {
        return getPrev(nums, i - 1, used) * nums[i] * getNext(nums, i + 1, used);
    }

    private int getNext(int[] nums, int i, Set<Integer> used) {
        if (i == nums.length) {
            return 1;
        }
        if (used.contains(i)) {
            return getNext(nums, i + 1, used);
        }
        return nums[i];
    }

    private int getPrev(int[] nums, int i, Set<Integer> used) {
        if (i < 0) {
            return 1;
        }
        if (used.contains(i)) {
            return getPrev(nums, i - 1, used);
        }
        return nums[i];
    }
}

class Solution {
    public int maxCoins(int[] nums) {
        int[] copy = new int[nums.length + 2];

        int j = 1;
        for (int num : nums) {
            if (num > 0) {
                copy[j++] = num;
            }
        }
        copy[0] = 1;
        copy[j] = 1;

        return recursion(copy, 0, j);
    }

    private int recursion(int[] nums, int start, int end) {
        if (end - start < 1) {
            return 0;
        }

        int maxCoins = 0;
        for (int i = start + 1; i < end; i++) {
            maxCoins = Math.max(
                    maxCoins,
                    nums[start] * nums[i] * nums[end] + recursion(nums, start, i) + recursion(nums, i, end)
            );
        }

        return maxCoins;
    }
}

// Add memo
class Solution {
    int[][] memo;

    public int maxCoins(int[] nums) {
        int[] copy = new int[nums.length + 2];

        int j = 1;
        for (int num : nums) {
            if (num > 0) {
                copy[j++] = num;
            }
        }
        copy[0] = 1;
        copy[j] = 1;

        memo = new int[j + 1][j + 1];
        return recursion(copy, 0, j);
    }

    private int recursion(int[] nums, int start, int end) {
        if (end - start < 1) {
            return 0;
        }
        if (memo[start][end] != 0) {
            return memo[start][end];
        }

        int maxCoins = 0;
        for (int i = start + 1; i < end; i++) {
            maxCoins = Math.max(
                    maxCoins,
                    nums[start] * nums[i] * nums[end] + recursion(nums, start, i) + recursion(nums, i, end)
            );
        }
        memo[start][end] = maxCoins;

        return maxCoins;
    }
}