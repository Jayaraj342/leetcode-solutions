class Solution {
    public int maxSumMinProduct(int[] nums) {
        List<Integer> prefixSums = new ArrayList<>();
        prefixSums.add(0);
        for (int num : nums) {
            int prevSum = prefixSums.get(prefixSums.size() - 1);
            prefixSums.add(num + prevSum);
        }

        Stack<Integer[]> stack = new Stack<>();// (value, index)
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            int tempI = i;
            while (!stack.isEmpty() && stack.peek()[0] > nums[i]) {
                Integer[] lastMax = stack.pop();
                // prefixSum.get(4) will give sum of indexes 0,1,2,3
                max = Math.max(max, lastMax[0] * (prefixSums.get(i) - prefixSums.get(lastMax[1])));
                tempI = lastMax[1];
            }
            stack.push(new Integer[]{nums[i], tempI});
        }

        for (Integer[] monoIncr : stack) {
            max = Math.max(max, monoIncr[0] * (prefixSums.get(nums.length) - prefixSums.get(monoIncr[1])));
        }

        return max;
    }
}