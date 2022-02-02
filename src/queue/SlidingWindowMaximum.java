class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> queue = new ArrayDeque<>();
        int[] res = new int[nums.length - k + 1];
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            while (!queue.isEmpty() && queue.peek() <= i - k) {
                queue.poll();
            }

            queue.add(i);

            if (i >= k - 1) {
                res[count++] = nums[queue.peek()];
            }
        }
        return res;
    }
}