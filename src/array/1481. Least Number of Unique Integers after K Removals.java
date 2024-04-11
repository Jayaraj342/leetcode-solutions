class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int n = arr.length;
        int[] freqList = new int[n + 1];
        for (int num : freqMap.values()) {
            freqList[num]++;
        }

        int res = freqMap.size();
        for (int freq = 1; freq <= n; freq++) {
            int occ = freqList[freq];
            if (k >= occ * freq) {
                k -= occ * freq;
                res -= occ;
            } else {
                res -= k / freq;
                break;
            }
        }

        return res;
    }
}

class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.addAll(freq.values());
        while (k > 0) {
            k -= minHeap.remove();
        }

        return minHeap.size() + (k == 0 ? 0 : 1);
    }
}