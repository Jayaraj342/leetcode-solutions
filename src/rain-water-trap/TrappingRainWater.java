class Solution {
    public int trap(int[] height) {
        int water = 0;
        int n = height.length;

        int left = 0;
        int right = n - 1;

        int leftMax = 0;
        int rightMax = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] > leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] > rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }

        return water;
    }
}

class Solution {
    public int trap(int[] height) {
        int n = height.length;

        int[] iterativeResults1 = new int[n];
        int currentMax = 0;
        for (int i = 0; i < n; i++) {
            if (height[i] < currentMax) {
                iterativeResults1[i] = currentMax - height[i];
            } else {
                currentMax = height[i];
            }
        }

        int[] iterativeResults2 = new int[n];
        currentMax = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (height[i] < currentMax) {
                iterativeResults2[i] = currentMax - height[i];
            } else {
                currentMax = height[i];
            }
        }

        int water = 0;
        for (int i = 0; i < n; i++) {
            water += Math.min(iterativeResults1[i], iterativeResults2[i]);
        }

        return water;
    }
}