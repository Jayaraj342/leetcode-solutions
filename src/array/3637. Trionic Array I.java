// n, 1
class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;

        int cnt = 0; // counts valid ups/downs
        for (int i = 1; i < n - 1; i++) {
            // consecutive duplicates -> invalid
            if (nums[i] == nums[i - 1] || nums[i] == nums[i + 1]) return false;

            boolean peak = nums[i] > nums[i - 1] && nums[i] > nums[i + 1];
            boolean valley = nums[i] < nums[i - 1] && nums[i] < nums[i + 1];

            if (peak || valley) {
                // valid next transition should match expected cnt order
                if (cnt == 0 && peak) cnt++;
                else if (cnt == 1 && valley) cnt++;
                else return false;
            }
        }

        return cnt == 2;
    }
}