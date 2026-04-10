class Solution {
    public long minOperations(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int lastNum = nums2[n];

        long res = 0, closestNum = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int lo = nums1[i], hi = nums2[i];
            if (lo > hi) {
                int temp = lo;
                lo = hi;
                hi = temp;
            }
            res += hi - lo;

            // if lastNum is between lo & hi, then it will be copied during change
            if (lastNum >= lo && lastNum <= hi) {
                closestNum = 0;
            } else {
                int min = Math.min(Math.abs(lo - lastNum), Math.abs(hi - lastNum));
                closestNum = Math.min(closestNum, min);
            }
        }

        return res + closestNum + 1;
    }
}