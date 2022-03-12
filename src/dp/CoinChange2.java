class Solution {
    public int change(int amount, int[] coins) {
        return dfs(amount, 0, coins);
    }

    Map<String, Integer> memo = new HashMap<>();

    public int dfs(int amount, int start, int[] coins) {
        if (amount < 0) {
            return 0;
        }

        if (amount == 0) {
            return 1;
        }

        String key = amount + "," + start;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int count = 0;
        for (int i = start; i < coins.length; ++i) {
            count += dfs(amount - coins[i], i, coins);
        }

        memo.put(key, count);
        return count;
    }
}

