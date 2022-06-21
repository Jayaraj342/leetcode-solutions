class Solution {
    public int numSubmat(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[] arr = new int[n];
        int sum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    arr[j] = 0;
                } else {
                    arr[j]++;
                }
            }
            sum += findRectangles(arr, n);
        }

        return sum;
    }

    private int findRectangles(int[] arr, int n) {
        Stack<Integer> stack = new Stack<>();
        stack.add(-1);

        int sum = 0;
        for (int i = 0; i < n; i++) {
            while (stack.peek() != -1 && arr[stack.peek()] > arr[i]) {
                int lastIdx = stack.pop();
                sum += arr[lastIdx] * (i - lastIdx) * (lastIdx - stack.peek());
            }
            stack.add(i);
        }
        while (stack.peek() != -1) {
            int lastIdx = stack.pop();
            sum += arr[lastIdx] * (n - lastIdx) * (lastIdx - stack.peek());
        }

        return sum;
    }
}