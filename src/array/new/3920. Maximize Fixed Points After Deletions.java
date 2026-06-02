// https://leetcode.com/problems/maximize-fixed-points-after-deletions/description/
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/array/new/3920. Maximize Fixed Points After Deletions.java

class Solution {
    public int maxFixedPoints(int[] nums) {
        int n = nums.length;

        List<int[]> shifts = new ArrayList<>();

        // store {i - nums[i], nums[i]}
        for (int i = 0; i < n; i++) {
            if (nums[i] <= i) {
                shifts.add(new int[]{i - nums[i], nums[i]});
            }
        }

        // sort by shift, then by value
        shifts.sort((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        // LIS on values
        List<Integer> lis = new ArrayList<>();
        for (int[] p : shifts) {
            int val = p[1];

            int idx = Collections.binarySearch(lis, val);
            if (idx < 0) idx = -(idx + 1); // not found - returns insertion point -ve (idx-1)

            if (idx == lis.size()) {
                lis.add(val);
            } else {
                lis.set(idx, val);
            }
        }

        return lis.size();
    }
}