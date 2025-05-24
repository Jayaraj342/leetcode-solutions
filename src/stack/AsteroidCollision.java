class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int curr : asteroids) {
            boolean isDestroyed = false;

            while (!stack.isEmpty() && stack.peek() > 0 && curr < 0) {
                int sum = stack.peek() + curr;
                if (sum == 0) { // Both asteroids destroy each other
                    stack.pop();
                    isDestroyed = true;
                    break;
                } else if (sum < 0) { // Last asteroid is smaller, destroy it
                    stack.pop();
                } else { // Current asteroid is destroyed
                    isDestroyed = true;
                    break;
                }
            }

            if (!isDestroyed) {
                stack.push(curr);
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
