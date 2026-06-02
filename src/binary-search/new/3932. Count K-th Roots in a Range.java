// https://leetcode.com/problems/count-k-th-roots-in-a-range/description/
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/binary-search/new/3932. Count K-th Roots in a Range.java

class Solution {
    public int countKthRoots(int l, int r, int k) {
        // Find lower bound
        int lo = 0, hi = Math.min(r, (int) Math.pow(10, 9));
        long lowerBound = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            long kthPow = pow(mid, k, r);
            if (kthPow >= l) {
                lowerBound = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        // Find upper bound
        lo = 0;
        hi = Math.min(r, (int) Math.pow(10, 9));
        long upperBound = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            long kthPow = pow(mid, k, r);
            if (kthPow <= r) {
                upperBound = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        if (upperBound == -1 || lowerBound == -1) {
            return 0;
        }

        return (int) (upperBound - lowerBound + 1);
    }

    private long pow(int num, int k, int limit) {
        if (num == 0) {
            return 0;
        }
        long a = num, res = 1L;
        while (k > 0) {
            if (k % 2 != 0) {
                res *= a;
            }
            if (res > limit || a > limit) {
                return limit + 1;
            }
            a *= a;
            k /= 2;
        }

        return res;
    }

    public static void main(String[] args) {
        new Solution().countKthRoots(147, 526, 9);
    }
}