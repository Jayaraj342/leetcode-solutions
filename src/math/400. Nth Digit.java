class Solution {
    // length 1 = 9 nums
    // length 2 = 90 nums
    // length 3 = 900 nums...
    public int findNthDigit(int n) {
        long start = 1, count = 9;
        int len = 1;
        while (n > len * count) {
            n -= len * count;
            len++;
            count *= 10;
            start *= 10;
        }

        // In case of n = 191 (ans = 2nd of 100), now n = (191 - 9 - 90 * 2) = 2, start = 100, len = 3

        start += (n - 1) / len;
        String strNum = String.valueOf(start);

        return strNum.charAt((n - 1) % len) - '0';
    }
}