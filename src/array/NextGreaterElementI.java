class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nge2 = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for(int num2 : nums2) {
            if(!stack.isEmpty()) {
                while(!stack.isEmpty() && stack.peek() < num2) {
                    nge2.put(stack.pop(), num2);
                }
            }
            stack.add(num2);
        }

        int[] nge1 = new int[nums1.length];
        int i = 0;
        for (int num : nums1) {
            nge1[i++] = nge2.getOrDefault(num, -1);
        }

        return nge1;
    }
}

// 503. Next Greater Element II

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] nge = new int[n];

        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            stack.add(nums[i]);
        }

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            nge[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.add(nums[i]);
        }
        return nge;
    }
}