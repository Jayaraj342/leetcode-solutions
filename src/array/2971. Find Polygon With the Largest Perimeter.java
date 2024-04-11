class Solution {
    public long largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        long sum = nums[0] + nums[1];

        long max = -1;
        for (int i = 2; i < n; i++) {
            if (sum > nums[i]) {
                max = Math.max(max, sum + nums[i]);
            }
            sum += nums[i];
        }

        return max;
    }
}

class Solution {
    public long largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        long[] pf = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            pf[i] = nums[i - 1] + pf[i - 1];
        }

        long max = -1;
        for (int i = 2; i < n; i++) {
            if (pf[i] > nums[i]) {
                max = Math.max(max, pf[i] + nums[i]);
            }
        }

        return max;
    }
}