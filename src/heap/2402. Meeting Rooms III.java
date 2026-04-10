class Solution {

    public int mostBooked(int n, int[][] meetings) {
        // Sort meetings by start time
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        // Busy rooms: earliest ending room first, tie → smaller room id
        PriorityQueue<Room> busy = new PriorityQueue<>(
                (a, b) -> a.endTime != b.endTime
                        ? Long.compare(a.endTime, b.endTime)
                        : Integer.compare(a.id, b.id)
        );

        // Free rooms: smallest room index first
        PriorityQueue<Integer> free = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            free.offer(i);
        }

        int[] usage = new int[n];
        for (int[] meeting : meetings) {
            long start = meeting[0], duration = meeting[1] - meeting[0];

            // Free rooms that have completed meetings
            while (!busy.isEmpty() && busy.peek().endTime <= start) {
                free.offer(busy.poll().id);
            }

            if (!free.isEmpty()) {
                int roomId = free.remove();
                busy.offer(new Room(start + duration, roomId));
                usage[roomId]++;
            } else {
                Room earliest = busy.remove();
                busy.offer(new Room(earliest.endTime + duration, earliest.id));
                usage[earliest.id]++;
            }
        }

        // Find room with maximum usage
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (usage[i] > usage[ans]) {
                ans = i;
            }
        }
        return ans;
    }

    static class Room {
        long endTime;
        int id;

        Room(long endTime, int id) {
            this.endTime = endTime;
            this.id = id;
        }
    }
}
