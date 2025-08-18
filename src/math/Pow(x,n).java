class Solution {
    public double myPow(double x, int n) {
        long exp = n;  // store as long to prevent overflow
        if (exp < 0) {
            x = 1 / x;
            exp = -exp; // safe now, because long can hold |Integer.MIN_VALUE|
        }

        double res = 1.0;
        while (exp > 0) {
            if ((exp & 1) == 1) {  // check if current bit is set
                res *= x;
            }
            x *= x;
            exp >>= 1;  // divide by 2
        }
        return res;
    }
}

class Solution {
    public double myPow(double x, int n) {
        if (n == Integer.MIN_VALUE) {
            // Take one factor out to avoid overflow (because there is no -Integer.MIN_VALUE in java)
            return myPow(x, n + 1) / x;
        }
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }

        double res = 1;
        while (n > 0) {
            if (n % 2 != 0) {
                res *= x;
            }
            x *= x;
            n /= 2;
        }

        return res;
    }
}

class Solution {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 1 / x * myPow(1 / x, -(n + 1));// Take one factor out to avoid overflow (because there is no -Integer.MIN_VALUE in java)
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