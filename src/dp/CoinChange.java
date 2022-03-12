class Solution {
    Map<Integer, Integer> memo = new HashMap<>();

    public int coinChange(int[] coins, int amount) {
        if (memo.containsKey(amount)) {
            return memo.get(amount);
        }

        if (amount < 0) return -1;
        if (amount == 0) return 0;

        int totalCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            int remainder = coinChange(coins, amount - coin);
            if (remainder != -1) {
                totalCoins = Math.min(totalCoins, 1 + remainder);
            }
        }
        memo.put(amount, totalCoins == Integer.MAX_VALUE ? -1 : totalCoins);

        return memo.get(amount);
    }
}
