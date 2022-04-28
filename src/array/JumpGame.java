class Solution {
    public boolean canJump(int[] nums) {
        int goal = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= goal) {
                goal = i;
            }
        }
        return goal == 0;
    }
}

//Jump game 2
class Solution {
    public int jump(int[] nums) {
        int result = 0;
        int farthestFromPreviousGroup = 0;
        int farthestFromCurrentGroup = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            farthestFromCurrentGroup = Math.max(farthestFromCurrentGroup, i + nums[i]);
            if (i == farthestFromPreviousGroup) {
                farthestFromPreviousGroup = farthestFromCurrentGroup;
                result++;
            }
        }

        return result;
    }
}