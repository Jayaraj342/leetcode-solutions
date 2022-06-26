// O(n.log(k))
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.add(num);
            if (minHeap.size() == k + 1) {
                minHeap.remove();
            }
        }

        return minHeap.remove();
    }
}

// Avg-O(n) and max O(n^2) for quick select
class Solution {
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickSelect(int[] nums, int low, int high, int k) {
        int pivot = nums[high];
        int i = low;

        for (int j = low; j < high; j++) {
            if (nums[j] < pivot) {
                swap(nums, i++, j);
            }
        }
        swap(nums, i, high);

        if (i == k) {
            return nums[i];
        } else if (i > k) {
            return quickSelect(nums, low, i - 1, k);
        }
        return quickSelect(nums, i + 1, high, k);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}