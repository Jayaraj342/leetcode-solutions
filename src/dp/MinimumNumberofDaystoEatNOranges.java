class Solution {
    Map<Integer, Integer> memo = new HashMap<>();

    public int minDays(int n) {
        if (n <= 2) {
            return n;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        memo.put(n, 1 + Math.min(n % 2 + minDays(n / 2), n % 3 + minDays(n / 3)));
        return memo.get(n);
    }
}