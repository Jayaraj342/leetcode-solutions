class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int curr : asteroids) {
            if (!stack.isEmpty() && curr < 0 && stack.peek() > 0) {
                while (!stack.isEmpty() && curr < 0 && stack.peek() > 0) {
                    int sum = stack.peek() + curr;
                    if (sum == 0) {
                        stack.pop();
                        curr = 0;
                    } else {
                        if (sum < 0) {
                            stack.pop();
                        } else {
                            curr = 0;
                        }
                    }
                }
            }
            if (curr != 0) {
                stack.add(curr);
            }
        }
        int[] res = new int[stack.size()];
        int i = 0;
        for (int s : stack) {
            res[i++] = s;
        }
        return res;
    }
}