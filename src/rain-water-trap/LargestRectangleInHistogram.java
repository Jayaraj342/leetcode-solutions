class Solution {
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        int n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || heights[i] < heights[stack.peek()])) {
                int h = heights[stack.pop()];
                int left = stack.isEmpty() ? -1 : stack.peek();

                int current = h * (i - left - 1);
                max = Math.max(max, current);
            }
            stack.push(i);
        }

        return max;
    }
}