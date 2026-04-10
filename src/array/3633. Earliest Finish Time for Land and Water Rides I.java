// O(m + n), O(1)
class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {
        int m = landStartTime.length, n = waterStartTime.length;

        // Step 1: Find the minimum land finish time
        int minLandFinish = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            minLandFinish = Math.min(minLandFinish, landStartTime[i] + landDuration[i]);
        }

        // Step 2: Find the minimum water finish time
        int minWaterFinish = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            minWaterFinish = Math.min(minWaterFinish, waterStartTime[i] + waterDuration[i]);
        }

        int ans = Integer.MAX_VALUE;

        // Step 3: Case 1 -> Land first, then Water
        for (int i = 0; i < n; i++) {
            int finishTime = waterDuration[i] + Math.max(minLandFinish, waterStartTime[i]);
            ans = Math.min(ans, finishTime);
        }

        // Step 4: Case 2 -> Water first, then Land
        for (int i = 0; i < m; i++) {
            int finishTime = landDuration[i] + Math.max(minWaterFinish, landStartTime[i]);
            ans = Math.min(ans, finishTime);
        }

        return ans;
    }
}