class Solution {
    public int mySqrt(int x) {
        if(x <= 1) return x;
        int lo = 0;
        int hi = x / 2;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2 + 1;
            long midSquare = (long) mid * mid;
            if (midSquare == x) {
                return mid;
            }
            if (midSquare < x) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }

        return lo;
    }
}