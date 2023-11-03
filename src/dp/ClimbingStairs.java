class Solution {
    public int climbStairs(int n) {
        int one = 1, two = 1;
        for (int i = 0; i < n - 1; i++) {
            int temp = one;
            one += two;
            two = temp;
        }

        return one;
    }
}

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