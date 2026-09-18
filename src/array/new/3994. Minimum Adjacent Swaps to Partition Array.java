// https://leetcode.com/problems/minimum-adjacent-swaps-to-partition-array
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/array/new/3994. Minimum Adjacent Swaps to Partition Array.java

class Solution {
    public int minAdjacentSwaps(int[] nums, int a, int b) {
        int MOD = 1_000_000_007;

        int cnt1 = 0, cnt2 = 0;
        long res = 0;
        for (int num : nums) {
            if (num < a) {
                res += cnt1 + cnt2;
            } else if (num <= b) {
                res += cnt2;
                cnt1++;
            } else {
                cnt2++;
            }

            res %= MOD;
        }

        return (int) res;
    }
}