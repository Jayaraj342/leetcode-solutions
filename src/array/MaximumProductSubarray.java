class Solution {
    public int maxProduct(int[] nums) {
        int res = Integer.MIN_VALUE;
        int max = 1, min = 1;

        for (int num : nums) {
            int temp = num * max;
            max = Math.max(num, Math.max(temp, num * min));
            min = Math.min(num, Math.min(temp, num * min));

            res = Math.max(res, max);
        }

        return res;
    }
}

class Solution {
    public int maxProduct(int[] nums) {
        int res = Integer.MIN_VALUE;
        int left = 1, right = 1;

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (left == 0) {
                left = 1;
            }
            if (right == 0) {
                right = 1;
            }
            left *= nums[i];
            right *= nums[n - i - 1];
            res = Math.max(res, Math.max(left, right));
        }

        return res;
    }
}