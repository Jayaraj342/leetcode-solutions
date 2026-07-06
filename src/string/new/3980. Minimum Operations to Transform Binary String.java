// https://leetcode.com/problems/minimum-operations-to-transform-binary-string
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/string/new/3980. Minimum Operations to Transform Binary String.java

// n, n
class Solution {
    public int minOperations(String s1, String s2) {
        if (s1.equals("1") && s2.equals("0")) {
            return -1;
        }

        int n = s1.length();
        char[] arr1 = s1.toCharArray();
        int op = 0;
        for (int i = 0; i < n; i++) {
            if (arr1[i] == s2.charAt(i)) {
                continue;
            }

            op++;
            if (arr1[i] == '1') {
                if (i == n - 1) {
                    op++;
                } else {
                    op += arr1[i + 1] == '0' ? 1 : 0;
                    arr1[i + 1] = '0';
                }
            }
        }

        return op;
    }
}