// https://leetcode.com/problems/construct-uniform-parity-array-ii/
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/array/new/3876. Construct Uniform Parity Array II.java

class Solution {
    public boolean uniformArray(int[] nums1) {
        int odd = 0, minOdd = Integer.MAX_VALUE;
        for (int num : nums1) {
            if (num % 2 == 1) {
                odd++;
                minOdd = Math.min(minOdd, num);
            }
        }

        if (odd == 0) {
            return true;
        }

        for (int num : nums1) {
            if (num % 2 == 0 && num < minOdd) {
                return false;
            }
        }

        return true;
    }
}