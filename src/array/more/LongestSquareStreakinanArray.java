// https://leetcode.com/problems/longest-square-streak-in-an-array/
class Solution {
    public int longestSquareStreak(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n / 2; ++i) {
            int temp = nums[i];
            nums[i] = nums[n - i - 1];
            nums[n - i - 1] = temp;
        }

        Map<Integer, Integer> memo = new HashMap<>();
        int max = -1;
        for (int num : nums) {
            if (memo.containsKey(num * num)) {
                int curr = memo.get(num * num) + 1;
                memo.put(num, curr);
                max = Math.max(max, curr);
            } else {
                memo.put(num, 1);
            }
        }

        return max;
    }
}