// intuitive
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Status> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i < n; i++) {
            int tempI = i;
            while (!stack.isEmpty() && stack.peek().h > heights[i]) {
                Status last = stack.pop();
                max = Math.max(max, (i - last.idx) * last.h);
                tempI = last.idx;
            }
            stack.push(new Status(heights[i], tempI));
        }

        while (!stack.isEmpty()) {
            Status last = stack.pop();
            max = Math.max(max, (n - last.idx) * last.h);
        }

        return max;
    }

    static class Status {
        int h;
        int idx;

        Status(int h, int idx) {
            this.h = h;
            this.idx = idx;
        }
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