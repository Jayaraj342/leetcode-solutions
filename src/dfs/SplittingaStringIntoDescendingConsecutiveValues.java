class Solution {
    public boolean splitString(String s) {
        int n = s.length();

        if (n == 1) {
            return false;
        }

        long first = 0;
        for (int r = 0; r < n - 1; r++) {
            first = first * 10 + (s.charAt(r) - '0');
            if (dfs(s, first, r + 1)) {
                return true;
            }
        }

        return false;
    }

    private boolean dfs(String s, long prev, int l) {
        if (l == s.length()) {
            return true;
        }

        boolean result = false;
        long current = 0;
        for (int r = l; r < s.length(); r++) {
            current = current * 10 + (s.charAt(r) - '0');
            if (prev - current == 1) {
                result |= dfs(s, current, r + 1);
            }
        }

        return result;
    }
}