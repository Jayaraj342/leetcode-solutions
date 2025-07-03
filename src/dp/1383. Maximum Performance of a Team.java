import java.util.*;

class Solution {
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        List<int[]> engineers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            engineers.add(new int[]{efficiency[i], speed[i]});
        }

        // Sort by efficiency in descending order
        engineers.sort((a, b) -> b[0] - a[0]);

        PriorityQueue<Integer> speedHeap = new PriorityQueue<>(); // min-heap
        long teamSpeed = 0, maxPerformance = 0;

        for (int[] engineer : engineers) {
            int currEff = engineer[0], currSpeed = engineer[1];

            speedHeap.add(currSpeed);
            teamSpeed += currSpeed;

            if (speedHeap.size() > k) {
                teamSpeed -= speedHeap.remove(); // remove smallest speed
            }

            maxPerformance = Math.max(maxPerformance, teamSpeed * currEff);
        }

        return (int) (maxPerformance % 1_000_000_007);
    }
}
