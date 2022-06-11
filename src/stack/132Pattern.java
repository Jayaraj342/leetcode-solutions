class Solution {
    public boolean find132pattern(int[] nums) {
        Stack<List<Integer>> stack = new Stack<>();
        int min = nums[0];
        for (int num : nums) {
            while (!stack.isEmpty() && stack.peek().get(0) <= num) {
                stack.pop();
            }
            if (!stack.isEmpty() && num > stack.peek().get(1)) {
                return true;
            }
            stack.add(List.of(num, min));
            min = Math.min(num, min);
        }

        return false;
    }
}