class Solution {
    // stop and count at exact k times
    public long countSubarrays(int[] nums, int k) {
        int n = nums.length, max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }

        int cnt = 0, i = 0;
        long res = 0;
        for (int j = 0; j < n; j++) {
            cnt += nums[j] == max ? 1 : 0;
            while (cnt > k || (i <= j && nums[i] != max)) {
                if (nums[i] == max) {
                    cnt--;
                }
                i++;
            }
            if (cnt == k) {
                res += i + 1;
            }
        }

        return res;
    }
}