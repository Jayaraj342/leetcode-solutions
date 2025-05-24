class Solution {
    public int minOperations(int[][] grid, int x) {
        List<Integer> values = new ArrayList<>();

        // Flatten the grid into a list
        for (int[] row : grid) {
            for (int num : row) {
                values.add(num);
            }
        }

        // Sort the values to find the median
        Collections.sort(values);
        int median = values.get(values.size() / 2);
        int operations = 0;

        // Calculate operations needed
        for (int num : values) {
            int diff = Math.abs(num - median);
            if (diff % x != 0) {
                return -1;
            }
            operations += diff / x;
        }

        return operations;
    }
}