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