class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int i = 0, j = height.length - 1;
        while (i < j) {
            int area;

            if (height[j] > height[i]) {
                area = (j - i) * height[i];
                i++;
            } else {
                area = (j - i) * height[j];
                j--;
            }
            if (area > maxArea) {
                maxArea = area;
            }
        }
        return maxArea;
    }
}