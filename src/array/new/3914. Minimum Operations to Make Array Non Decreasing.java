// https://leetcode.com/problems/minimum-operations-to-make-array-non-decreasing/
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/array/new/3914. Minimum Operations to Make Array Non Decreasing.java
class Solution {
    public long minOperations(int[] nums) {
        long res = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            res += Math.max(0, nums[i] - nums[i + 1]);
        }

        return res;
    }
}