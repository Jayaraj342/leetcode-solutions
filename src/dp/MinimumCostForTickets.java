class Solution {
    Map<Integer, Integer> memo = new HashMap<>();
    int[] dayPasses = {1, 7, 30};

    public int mincostTickets(int[] days, int[] costs) {
        return dfs(days, costs, 0);
    }

    private int dfs(int[] days, int[] costs, int index) {
        if (memo.containsKey(index)) {
            return memo.get(index);
        }
        if (index == days.length) {
            return 0;
        }

        int minForCurrentIndex = Integer.MAX_VALUE;
        for (int i = 0; i < costs.length; i++) {
            int newIndex = index;
            while (newIndex < days.length && days[newIndex] < days[index] + dayPasses[i]) {
                newIndex++;
            }
            minForCurrentIndex = Math.min(minForCurrentIndex, costs[i] + dfs(days, costs, newIndex));
        }

        memo.put(index, minForCurrentIndex);
        return minForCurrentIndex;
    }
}