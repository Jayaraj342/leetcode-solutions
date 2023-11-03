class Solution {
    public int sumSubarrayMins(int[] arr) {
        final int MOD = 1_000_000_007;
        int n = arr.length;

        Stack<Integer> stack = new Stack<>();
        stack.add(-1);

        long sum = 0;
        for (int i = 0; i < n; i++) {
            while (stack.peek() != -1 && arr[i] < arr[stack.peek()]) {
                int lastIndex = stack.pop();
                sum = (sum + (long) arr[lastIndex] * (i - lastIndex) * (lastIndex - stack.peek())) % MOD;
            }
            stack.add(i);
        }
        while (!(stack.peek() == -1)) {
            int lastIndex = stack.pop();
            sum = (sum + (long) arr[lastIndex] * (n - lastIndex) * (lastIndex - stack.peek())) % MOD;
        }

        return (int) sum;
    }
}