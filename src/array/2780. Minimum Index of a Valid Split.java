class Solution {
    public int minimumIndex(List<Integer> nums) {
        int dominant = findDominant(nums);
        int totalCount = countOccurrences(nums, dominant);

        int leftCount = 0, rightCount = totalCount;
        int n = nums.size();
        for (int i = 0; i < n; i++) {
            if (nums.get(i) == dominant) {
                leftCount++;
                rightCount--;
            }

            int leftSize = i + 1, rightSize = n - leftSize;
            if (leftCount * 2 > leftSize && rightCount * 2 > rightSize) {
                return i;
            }
        }

        return -1;
    }

    private int findDominant(List<Integer> nums) {
        int candidate = 0, count = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

    private int countOccurrences(List<Integer> nums, int target) {
        int count = 0;
        for (int num : nums) {
            if (num == target) count++;
        }

        return count;
    }
}
