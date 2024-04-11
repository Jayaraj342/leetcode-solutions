class Solution {
    public boolean isPowerOfTwo(int n) {
        return n >= 1 && (n & (n - 1)) == 0;
    }
}

class Solution {
    public boolean isPowerOfTwo(int n) {
        return n >= 1 && (Math.pow(2, 30) % n) == 0;
    }
}