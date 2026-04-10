// n * log(26) -> n, 1
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freqMap = new int[26];
        for (char task : tasks) {
            freqMap[task - 'A']++;
        }

        // Max-heap to pick the most frequent task
        PriorityQueue<Task> maxHeap = new PriorityQueue<>((a, b) -> b.freq - a.freq);
        // Min-heap to manage cooling tasks based on next available time
        PriorityQueue<Task> coolingQueue = new PriorityQueue<>((a, b) -> a.nextAvailable - b.nextAvailable);

        for (int freq : freqMap) {
            if (freq > 0) {
                maxHeap.add(new Task(freq));
            }
        }

        int time = 0;
        while (!maxHeap.isEmpty() || !coolingQueue.isEmpty()) {
            // Advance time if no tasks are available (CPU idle)
            if (maxHeap.isEmpty()) {
                time = coolingQueue.peek().nextAvailable;
            } else {
                time++;
            }

            // Move all tasks whose cooldown has ended back to the max-heap
            while (!coolingQueue.isEmpty() && coolingQueue.peek().nextAvailable <= time) {
                maxHeap.add(coolingQueue.remove());
            }

            Task current = maxHeap.remove();
            current.nextAvailable = time + n + 1;
            current.freq--;

            if (current.freq > 0) {
                coolingQueue.add(current);
            }
        }

        return time;
    }

    static class Task {
        int freq;
        int nextAvailable;

        Task(int freq) {
            this.freq = freq;
            this.nextAvailable = 0;
        }
    }
}

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char task : tasks) {
            freq[task - 'A']++;
        }

        Arrays.sort(freq); // Sort to get the max frequency at the end
        int maxFreq = freq[25];
        int idleSlots = (maxFreq - 1) * n;

        // Fill idle slots with other tasks (starting from second most frequent)
        for (int i = 24; i >= 0 && idleSlots > 0; i--) {
            idleSlots -= Math.min(freq[i], maxFreq - 1);
        }

        idleSlots = Math.max(0, idleSlots); // no negative idles

        return tasks.length + idleSlots;
    }
}

// https://leetcode.com/problems/task-scheduler-ii/
class Solution {
    public long taskSchedulerII(int[] tasks, int space) {
        Map<Integer, Long> coolingPeriod = new HashMap<>();
        long time = 0;

        for (int task : tasks) {
            Long nextAvailable = coolingPeriod.get(task);
            if (nextAvailable != null && nextAvailable > time) {
                time = nextAvailable;
            }
            coolingPeriod.put(task, time + space + 1);
            time++;
        }

        return time;
    }
}