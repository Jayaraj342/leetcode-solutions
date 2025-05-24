class Solution {
    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }

        int lo = 1, hi = x / 2;
        int res = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            long midSq = (long) mid * mid;

            if (midSq <= x) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }

            if (midSq <= x) {
                res = mid;
            }
        }

        return res;
    }
}

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