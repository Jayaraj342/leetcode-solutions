class Solution {
    public int maxRemoval(int[] nums, int[][] queries) {
        Arrays.sort(queries, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> availableEndTimes = new PriorityQueue<>((a, b) -> b - a); // max-heap
        PriorityQueue<Integer> usedEndTimes = new PriorityQueue<>(); // min-heap

        int n = nums.length, m = queries.length;
        int k = 0, usedCount = 0;
        for (int i = 0; i < n; i++) {
            int required = nums[i];

            // Remove expired intervals
            while (!usedEndTimes.isEmpty() && usedEndTimes.peek() < i) {
                usedEndTimes.remove();
            }

            // Add new intervals starting at i
            while (k < m && queries[k][0] == i) {
                availableEndTimes.add(queries[k][1]);
                k++;
            }

            // Assign intervals until requirement met or no available (assign only if they have not expired)
            while (usedEndTimes.size() < required && !availableEndTimes.isEmpty() && availableEndTimes.peek() >= i) {
                usedEndTimes.add(availableEndTimes.remove());
                usedCount++;
            }

            if (usedEndTimes.size() < required) {
                return -1; // cannot meet requirement
            }
        }

        return m - usedCount;
    }
}
