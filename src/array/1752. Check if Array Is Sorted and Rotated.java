class Solution {
    public boolean check(int[] nums) {
        int dipCount = 0, prev = -1;
        for (int num : nums) {
            if (num < prev) {
                dipCount++;
            }
            prev = num;
        }

        // The array is sorted if there's at most one dip and the last element is not greater than the first
        return dipCount == 0 || (dipCount == 1 && nums[0] >= nums[nums.length - 1]);
    }
}