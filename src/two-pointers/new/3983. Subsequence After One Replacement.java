// https://leetcode.com/problems/subsequence-after-one-replacement
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/two-pointers/new/3983. Subsequence After One Replacement.java

class Solution {
    public boolean canMakeSubsequence(String s, String t) {
        if (s.length() > t.length()) return false;

        int m = s.length(), n = t.length();

        int[] left = new int[m];
        int[] right = new int[m];

        Arrays.fill(left, -1);
        Arrays.fill(right, -1);

        int j = 0;
        for (int i = 0; i < m; i++) {
            while (j < n && t.charAt(j) != s.charAt(i)) {
                j++;
            }
            if (j == n) break;
            left[i] = j++;
        }

        if (left[m - 1] != -1) return true;

        j = n - 1;
        for (int i = m - 1; i >= 0; i--) {
            while (j >= 0 && t.charAt(j) != s.charAt(i)) {
                j--;
            }
            if (j < 0) break;
            right[i] = j--;
        }

        for (int i = 0; i < m; i++) {
            if ((i == 0 || left[i - 1] != -1) && (i == m - 1 || right[i + 1] != -1)) {

                int L = (i == 0) ? -1 : left[i - 1];
                int R = (i == m - 1) ? n : right[i + 1];

                if (L + 1 < R) {
                    return true;
                }
            }
        }

        return false;
    }
}