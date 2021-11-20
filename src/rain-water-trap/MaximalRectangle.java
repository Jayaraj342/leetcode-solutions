class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;

        int max = 0;
        int[] arr = new int[n];
        for (char[] chars : matrix) {
            for (int j = 0; j < n; j++) {
                if (chars[j] == '0') {
                    arr[j] = 0;
                } else {
                    arr[j] = arr[j] + 1;
                }
            }

            max = Math.max(max, maximalRectangeInArray(arr, n));
        }

        return max;
    }

    private int maximalRectangeInArray(int[] arr, int n) {
        Deque<Integer> stack = new ArrayDeque<>();

        int max = 0;
        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || arr[i] < arr[stack.peek()])) {
                int h = arr[stack.pop()];
                int left = stack.isEmpty() ? -1 : stack.peek();
                max = Math.max(max, h * (i - left - 1));
            }
            stack.push(i);
        }

        return max;
    }
}