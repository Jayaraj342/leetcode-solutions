class Solution {
    Map<Integer, Integer> memo = new HashMap<>();

    public int climbStairs(int n) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        if (n == 1 || n == 2) {
            return n;
        }

        memo.put(n, climbStairs(n - 1) + climbStairs(n - 2));
        return memo.get(n);
    }
}