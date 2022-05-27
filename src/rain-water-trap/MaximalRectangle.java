class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] heights = new int[n];

        int max = 0;
        for (char[] row : matrix) {
            for (int j = 0; j < n; j++) {
                if (row[j] == '0') {
                    heights[j] = 0;
                } else {
                    heights[j] += 1;
                }
            }
            max = Math.max(max, maxRectangleInAnArray(heights, n));
        }

        return max;
    }

    private int maxRectangleInAnArray(int[] heights, int n) {
        Stack<List<Integer>> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i < n; i++) {
            int tempI = i;
            while (!stack.isEmpty() && stack.peek().get(0) > heights[i]) {
                List<Integer> lastMax = stack.pop();
                max = Math.max(max, lastMax.get(0) * (i - lastMax.get(1)));
                tempI = lastMax.get(1);
            }
            // [height, index]
            stack.add(List.of(heights[i], tempI));
        }

        for (List<Integer> monoIncr : stack) {
            max = Math.max(max, monoIncr.get(0) * (heights.length - monoIncr.get(1)));
        }

        return max;
    }
}