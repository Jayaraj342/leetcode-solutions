// https://www.geeksforgeeks.org/minimum-number-of-sub-strings-of-a-string-such-that-all-are-power-of-5/
// https://practice.geeksforgeeks.org/problems/cutting-binary-string1342/1
class Solution {
    static int cuts(String s) {
        int count = dfs(s, 0);
        return count >= s.length() + 1 ? -1 : count;
    }

    private static int dfs(String s, int idx) {
        int n = s.length();
        if (idx == n) {
            return 0;
        }

        int count = n + 1;
        if (s.charAt(idx) == '0') {
            return count;
        }

        for (int i = idx; i < s.length(); i++) {
            if (s.charAt(i) == '1' && isPower(number(s, idx, i))) {
                count = Math.min(count, 1 + dfs(s, i + 1));
            }
        }

        return count;
    }

    private static long number(String s, int i, int j) {
        long ans = 0;
        for (int x = i; x <= j; x++) {
            ans = ans * 2 + (s.charAt(x) - '0');
        }
        return ans;
    }

    private static boolean isPower(long n) {
        if (n < 125) {
            return (n == 1 || n == 5 || n == 25);
        }
        if (n % 125 != 0) {
            return false;
        } else {
            return isPower(n / 125);
        }
    }
}