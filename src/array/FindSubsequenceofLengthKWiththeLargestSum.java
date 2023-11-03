// O(n.logk)
class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((i1, i2) -> nums[i1] - nums[i2]);
        for (int i = 0; i < nums.length; i++) {
            minHeap.add(i);
            if (minHeap.size() > k) {
                minHeap.remove();
            }
        }
        return minHeap.stream().sorted().mapToInt(i -> nums[i]).toArray();
    }
}

// avg n for quick select, so O(n)
class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        int n = nums.length;
        int[] copy = Arrays.copyOfRange(nums, 0, n);
        int kThLargest = quickSelect(copy, 0, n - 1, n - k);

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < k; i++) {
            map.put(copy[n - 1 - i], map.getOrDefault(copy[n - 1 - i], 0) + 1);
        }

        int[] res = new int[k];
        int j = 0;
        for (int i = 0; i < n && j < k; i++) {
            if (nums[i] >= kThLargest && map.getOrDefault(nums[i], 0) > 0) {
                res[j++] = nums[i];
                map.put(nums[i], map.get(nums[i]) - 1);
            }
        }

        return res;
    }

    private int quickSelect(int[] nums, int lo, int hi, int k) {
        int pivot = nums[hi];
        int idx = lo;
        for (int i = lo; i < hi; i++) {
            if (nums[i] < pivot) {
                swap(nums, i, idx++);
            }
        }
        swap(nums, hi, idx);
        if (idx == k) {
            return nums[k];
        }
        if (idx < k) {
            return quickSelect(nums, idx + 1, hi, k);
        } else {
            return quickSelect(nums, lo, idx - 1, k);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}