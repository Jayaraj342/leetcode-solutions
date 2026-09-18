class Solution {
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        int second = Integer.MIN_VALUE; // candidate for "2"
        for (int i = n - 1; i >= 0; i--) {
            // nums[i] can be "1"
            if (nums[i] < second) {
                return true;
            }

            // nums[i] can become a "3"
            // Anything popped is a valid "2"
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                second = stack.pop();
            }

            stack.push(nums[i]);
        }

        return false;
    }
}

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