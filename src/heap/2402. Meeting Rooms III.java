class Solution {
    public int mostBooked(int n, int[][] meetings) {
        PriorityQueue<Integer> available = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            available.add(i);
        }
        // [end, room]
        PriorityQueue<int[]> used = new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        int[] cnt = new int[n];
        for (int[] pair : meetings) {
            int start = pair[0], end = pair[1];

            while (!used.isEmpty() && start >= used.peek()[0]) {
                int[] finished = used.remove();
                available.add(finished[1]);
            }

            if (available.isEmpty()) {
                int[] finished = used.remove();
                int lastEnd = finished[0], lastRoom = finished[1];
                available.add(lastRoom);
                end = (end - start) + lastEnd;
            }

            int room = available.remove();
            used.add(new int[] {end, room});
            cnt[room]++;
        }

        int maxMeetings = -1, maxRoom = -1;
        for (int i = 0; i < n; i++) {
            if (cnt[i] > maxMeetings) {
                maxMeetings = cnt[i];
                maxRoom = i;
            }
        }

        return maxRoom;
    }
}