class Solution {
    public long countSubarrays(int[] nums, int k) {
        int n = nums.length, max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }

        int prevMaxIdx = -1, cnt = 0, j = 0;
        long res = 0;
        for (int i = 0; i < n; i++) {
            cnt += nums[i] == max ? 1 : 0;
            while (cnt > k || (j <= i && nums[j] != max)) {
                if (nums[j] == max) {
                    cnt--;
                }
                j++;
            }
            if (cnt == k) {
                res += j + 1;
            }
        }

        return res;
    }
}