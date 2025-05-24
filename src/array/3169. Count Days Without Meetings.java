// SC : O(1)
class Solution {
    public int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        int start = meetings[0][0], end = meetings[0][1];

        for (int i = 1; i < meetings.length; i++) {
            int currStart = meetings[i][0], currEnd = meetings[i][1];

            if (currStart > end) {
                days -= (end - start + 1);

                start = currStart;
                end = currEnd;
            } else {
                end = Math.max(end, currEnd);
            }
        }
        days -= (end - start + 1);

        return days;
    }
}

// SC : O(N)
class Solution {
    public int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        List<int[]> mergedMeetings = new ArrayList<>();
        int start = meetings[0][0], end = meetings[0][1];

        for (int i = 1; i < meetings.length; i++) {
            int currStart = meetings[i][0], currEnd = meetings[i][1];

            if (currStart > end) {
                mergedMeetings.add(new int[]{start, end});
                start = currStart;
                end = currEnd;
            } else {
                end = Math.max(end, currEnd);
            }
        }
        mergedMeetings.add(new int[]{start, end});

        for (int[] meeting : mergedMeetings) {
            days -= (meeting[1] - meeting[0] + 1);
        }

        return days;
    }
}

// time limit exceeded
class Solution {
    public int countDays(int days, int[][] meetings) {
        Map<Integer, Integer> availableDays = new HashMap<>();
        for (int[] meeting : meetings) {
            int start = meeting[0], end = meeting[1];
            availableDays.put(start, availableDays.getOrDefault(start, 0) + 1);
            availableDays.put(end + 1, availableDays.getOrDefault(end + 1, 0) - 1);
        }

        int cnt = 0, res = 0;
        for (int i = 1; i <= days; i++) {
            cnt += availableDays.getOrDefault(i, 0);
            if (cnt == 0) {
                res++;
            }
        }

        return res;
    }
}

// memory limit exceeded
class Solution {
    public int countDays(int days, int[][] meetings) {
        int[] availableDays = new int[days + 2];
        for (int[] meeting : meetings) {
            availableDays[meeting[0]]--;
            availableDays[meeting[1] + 1]++;
        }

        int cnt = 0, res = 0;
        for (int i = 1; i <= days; i++) {
            cnt += availableDays[i];
            if (cnt == 0) {
                res++;
            }
        }

        return res;
    }
}