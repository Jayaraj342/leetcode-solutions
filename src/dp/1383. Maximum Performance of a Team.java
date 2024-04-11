class Solution {
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new int[]{efficiency[i], speed[i]});
        }
        list.sort((a, b) -> b[0] - a[0]);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        long teamSpeed = 0, max = 0;
        for (int[] eng : list) {
            if (minHeap.size() == k) {
                teamSpeed -= minHeap.remove();
            }
            teamSpeed += eng[1];
            minHeap.add(eng[1]);

            max = Math.max(max, teamSpeed * eng[0]);
        }

        return (int) (max % 1000_000_007);
    }
}