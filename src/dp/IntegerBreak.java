class Solution {
    Map<Integer, Integer> memo = new HashMap<>();

    public int integerBreak(int n) {
        return dfs(n, n);
    }

    private int dfs(int n, int curr) {
        if (memo.containsKey(curr)) {
            return memo.get(curr);
        }
        int max = n == curr ? 1 : curr;
        for (int i = 1; i < curr; i++) {
            max = Math.max(max, dfs(n, i) * dfs(n, curr - i));
        }

        memo.put(curr, max);
        return max;
    }
}