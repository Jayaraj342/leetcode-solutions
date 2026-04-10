class Solution {
    public int countElements(int[] nums, int k) {
        if (k == 0) return nums.length;

        Arrays.sort(nums);
        int n = nums.length;

        // k-th largest element
        int threshold = nums[n - k];

        int res = 0;
        for (int num : nums) {
            if (num < threshold) {
                res++;
            }
        }

        return res;
    }
}

class Solution {
    public int countElements(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            int j = i;
            while (j < n && nums[j] == nums[i]) {
                j++;
            }

            int greaterElements = n - j;
            if (greaterElements >= k) {
                res += j - i;
            }
            i = j - 1;
        }

        return res;
    }
}