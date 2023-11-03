class Solution {
    Map<String, Boolean> memo = new HashMap<>();

    public boolean isMatch(String s, String p) {
        return dfs(s, p, 0, 0);
    }

    private boolean dfs(String s, String p, int i, int j) {
        if (j == p.length()) {
            return i == s.length();
        }

        String key = i + "," + j;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        boolean firstMatch = i != s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

        boolean ans;
        if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
            ans = firstMatch && dfs(s, p, i + 1, j) || dfs(s, p, i, j + 2);
        } else {
            ans = firstMatch && dfs(s, p, i + 1, j + 1);
        }
        memo.put(key, ans);
        return ans;
    }
}