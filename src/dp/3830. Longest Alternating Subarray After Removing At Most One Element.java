class Solution {
    public int longestAlternating(int[] nums) {
        int n = nums.length;

        int[][] end = new int[n][2];// left[i][0] -> valley, left[i][1] -> mountain => ending at i
        for (int[] pair : end) {
            Arrays.fill(pair, 1);
        }

        int[][] start = new int[n][2];// right[i][0] -> valley, right[i][1] -> mountain => starting at i
        for (int[] pair : start) {
            Arrays.fill(pair, 1);
        }
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                end[i][0] = end[i - 1][1] + 1;
            }
            if (nums[i] > nums[i - 1]) {
                end[i][1] = end[i - 1][0] + 1;
            }

            int j = n - 1 - i;
            if (nums[j] < nums[j + 1]) {
                start[j][0] = start[j + 1][1] + 1;
            }
            if (nums[j] > nums[j + 1]) {
                start[j][1] = start[j + 1][0] + 1;
            }
        }

        int max = 1;
        for (int[] pair : end) {// without removing
            for (int num : pair) {
                max = Math.max(max, num);
            }
        }
        for (int i = 1; i < n - 1; i++) {
            // removing current
            // then slope can be \
            if (nums[i - 1] > nums[i + 1]) {
                max = Math.max(max, end[i - 1][1] + start[i + 1][0]);
            } else if (nums[i - 1] < nums[i + 1]) {// slope is /
                max = Math.max(max, end[i - 1][0] + start[i + 1][1]);
            }
        }

        return max;
    }
}