class Solution {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 1 / x * myPow(1 / x, -(n + 1));
        }

        return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }
}

// ---------------------------------------------------------------------------------------

class Solution {
    public int pow(int x, int n, int d) {
        long res = 1L;
        long longX = x;
        while (n > 0) {
            if (n % 2 == 1) {
                res *= longX;
                res %= d;
            }
            longX *= longX;
            longX %= d;
            n /= 2;
        }
        res = (res + d) % d;

        return (int) res;
    }
}