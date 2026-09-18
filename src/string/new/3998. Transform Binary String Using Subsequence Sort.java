// https://leetcode.com/problems/transform-binary-string-using-subsequence-sort
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/string/new/3998. Transform Binary String Using Subsequence Sort.java

class Solution {
    public boolean[] transformStr(String s, String[] strs) {
        int zeros = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') zeros++;
        }
        int ones = s.length() - zeros;

        boolean[] res = new boolean[strs.length];
        for (int i = 0; i < strs.length; i++) {
            res[i] = check(s, strs[i], zeros, ones);
        }
        return res;
    }

    private boolean check(String s, String t, int zeros, int ones) {
        char[] arr = t.toCharArray();

        int cnt0 = 0, cnt1 = 0;
        for (char c : arr) {
            if (c == '0') cnt0++;
            else if (c == '1') cnt1++;
        }

        if (cnt0 > zeros || cnt1 > ones) {
            return false;
        }

        int need0 = zeros - cnt0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '?') {
                arr[i] = need0-- > 0 ? '0' : '1';
            }
        }

        int prefixOnesS = 0, prefixOnesT = 0;
        for (int i = 0; i < s.length(); i++) {
            prefixOnesS += s.charAt(i) - '0';
            prefixOnesT += arr[i] - '0';

            if (prefixOnesT > prefixOnesS) {
                return false;
            }
        }

        return true;
    }
}