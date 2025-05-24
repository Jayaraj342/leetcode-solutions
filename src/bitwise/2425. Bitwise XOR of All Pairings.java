class Solution {
    public int xorAllNums(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;

        int res = 0;
        // If nums1 has an odd length, each element of nums2 contributes to the final XOR result.
        if (m % 2 == 1) {
            for (int num : nums2) {
                res ^= num;
            }
        }

        // If nums2 has an odd length, each element of nums1 contributes to the final XOR result.
        if (n % 2 != 0) {
            for (int num : nums1) {
                res ^= num;
            }
        }

        return res;
    }
}