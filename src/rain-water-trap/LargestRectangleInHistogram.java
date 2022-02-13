class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer[]> stack = new Stack<>();// (value, index)
        int maxArea = 0;

        for (int i = 0; i < heights.length; i++) {
            int tempI = i;
            while (!stack.isEmpty() && stack.peek()[0] > heights[i]) {
                Integer[] lastMax = stack.pop();
                maxArea = Math.max(maxArea, lastMax[0] * (i - lastMax[1]));
                tempI = lastMax[1];
            }
            stack.push(new Integer[]{heights[i], tempI});
        }

        for (Integer[] monoIncr : stack) {
            maxArea = Math.max(maxArea, monoIncr[0] * (heights.length - monoIncr[1]));
        }

        return maxArea;
    }
}

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