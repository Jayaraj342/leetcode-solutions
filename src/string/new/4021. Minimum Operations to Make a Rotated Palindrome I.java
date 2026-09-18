// https://leetcode.com/problems/minimum-operations-to-make-a-rotated-palindrome-i
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/string/new/4021. Minimum Operations to Make a Rotated Palindrome I.java

class Solution {
    public int minOperations(String s) {
        int n = s.length();

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {// Rotations
            int op = i;
            for (int j = 0; j < n / 2; j++) {
                int a = (i + j) % n;
                int b = (i - 1 - j + n) % n;

                int diff = Math.abs(s.charAt(a) - s.charAt(b));
                op += Math.min(diff, 26 - diff);
            }

            min = Math.min(min, op);
        }

        return min;
    }
}