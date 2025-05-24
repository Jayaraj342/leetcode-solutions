class Solution {
    public boolean splitString(String s) {
        int n = s.length();
        long first = 0;

        for (int i = 0; i < n - 1; i++) {
            first = first * 10 + (s.charAt(i) - '0');

            if (dfs(s, i + 1, first)) {
                return true;
            }
        }

        return false;
    }

    private boolean dfs(String s, int index, long prev) {
        if (index == s.length()) {
            return true;
        }

        long current = 0;
        for (int i = index; i < s.length(); i++) {
            current = current * 10 + (s.charAt(i) - '0');
            if (prev - current == 1 && dfs(s, i + 1, current)) {
                return true;
            }
        }

        return false;
    }
}
