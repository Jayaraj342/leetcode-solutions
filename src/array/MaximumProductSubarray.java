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