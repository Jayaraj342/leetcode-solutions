class Solution {
    public int numTrees(int n) {
        List<Integer> dp = new ArrayList<>();
        dp.add(1);
        dp.add(1);

        for (int i = 2; i <= n; i++) {
            int combinations = 0;
            for (int j = 0; j < i; j++) {
                combinations += dp.get(j) * dp.get(i - j - 1);
            }
            dp.add(combinations);
        }

        return dp.get(n);
    }
}