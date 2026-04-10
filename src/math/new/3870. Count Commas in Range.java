// https://leetcode.com/problems/count-commas-in-range/
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/math/new/3870. Count Commas in Range.java
class Solution {
    public int countCommas(int n) {
        return Math.max(n - 999, 0);
    }
}

// https://leetcode.com/problems/count-commas-in-range-ii/
class Solution {
    public long countCommas(long n) {
        long res = 0, curr = 1000;
        for (int i = 1; i <= 5; ++i) {
            if (n >= curr) {
                res += n - curr + 1;
            }
            curr *= 1000;
        }

        return res;
    }
}