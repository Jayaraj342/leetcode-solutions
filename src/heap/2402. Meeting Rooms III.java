class Solution {
    public int mostBooked(int n, int[][] meetings) {
        // Sort meetings by start time
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));

        // Min-heap for available rooms (by room number)
        PriorityQueue<Integer> availableRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            availableRooms.add(i);
        }

        // Min-heap for ongoing meetings: [endTime, roomNumber]
        PriorityQueue<int[]> ongoingMeetings = new PriorityQueue<>(
                (a, b) -> {
                    if (b[0] != a[0]) {
                        return a[0] - b[0];// least end time
                    }

                    return a[1] - b[1];// least room number
                }
        );

        int[] usageCount = new int[n];
        int maxUsage = 0;

        for (int[] meeting : meetings) {
            int start = meeting[0], end = meeting[1];

            // Free up rooms whose meetings have ended
            while (!ongoingMeetings.isEmpty() && ongoingMeetings.peek()[0] <= start) {
                availableRooms.add(ongoingMeetings.poll()[1]);
            }

            if (availableRooms.isEmpty()) {
                // Delay this meeting until the earliest room is free
                int[] earliest = ongoingMeetings.remove();
                int room = earliest[1];
                int newEnd = earliest[0] + (end - start);
                ongoingMeetings.offer(new int[]{newEnd, room});
                usageCount[room]++;
                maxUsage = Math.max(maxUsage, usageCount[room]);
            } else {
                // Assign to earliest available room
                int room = availableRooms.remove();
                ongoingMeetings.add(new int[]{end, room});
                usageCount[room]++;
                maxUsage = Math.max(maxUsage, usageCount[room]);
            }
        }

        // Return smallest room number with max usage
        for (int i = 0; i < n; i++) {
            if (usageCount[i] == maxUsage) {
                return i;
            }
        }
        return -1; // Shouldn't reach here
    }
}