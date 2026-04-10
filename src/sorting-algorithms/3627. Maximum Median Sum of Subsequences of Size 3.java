// n.log(n), 1
class Solution {
    public long maximumMedianSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, k = n / 3;
        // pick every 2nd element from last k times
        int pick = n - 2;
        long median = 0;
        for (int i = 0; i < k; i++) {
            median += nums[pick];
            pick -= 2;
        }

        return median;
    }
}