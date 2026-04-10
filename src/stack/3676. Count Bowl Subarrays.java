// n, n
class Solution {
    public long bowlSubarrays(int[] A) {
        int n = A.length;
        long res = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // Pop until we find a taller "left boundary"
            while (!stack.isEmpty() && A[stack.peek()] <= A[i]) {
                stack.pop();
                // Only count when there's a valid left boundary
                if (!stack.isEmpty()) {
                    res++;
                }
            }
            stack.push(i);
        }

        return res;
    }
}

// n, n
class Solution {
    public long bowlSubarrays(int[] nums) {
        int n = nums.length;

        // max till now left -> right
        int[] maxLeft = Arrays.copyOf(nums, n);
        for (int i = 1; i < n - 1; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], maxLeft[i]);
        }

        // max till now right -> left
        int[] maxRight = Arrays.copyOf(nums, n);
        for (int i = n - 2; i > 0; i--) {
            maxRight[i] = Math.max(maxRight[i + 1], maxRight[i]);
        }

        long cnt = 0;
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] != maxLeft[i] && nums[i] != maxRight[i]) {
                cnt++;
            }
        }

        return cnt;
    }
}