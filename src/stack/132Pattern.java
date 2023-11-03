class Solution {
    public boolean find132pattern(int[] nums) {
        Stack<Status> stack = new Stack<>();
        int min = nums[0];
        for (int num : nums) {
            while (!stack.isEmpty() && stack.peek().val <= num) {
                stack.pop();
            }
            if (!stack.isEmpty() && num > stack.peek().prevMin) {
                return true;
            }
            stack.add(new Status(num, min));
            min = Math.min(num, min);
        }

        return false;
    }

    static class Status {
        int val;
        int prevMin;

        Status(int val, int prevMin) {
            this.val = val;
            this.prevMin = prevMin;
        }
    }
}