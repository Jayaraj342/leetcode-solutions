class Solution {
    public int longestSubsequence(int[] nums) {
        int max = 0;
        for (int i = 0; i < 32; i++) {
            int bit = 1 << i;
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                if ((num & bit) != 0) {
                    if (list.isEmpty() || list.get(list.size() - 1) < num) {
                        list.add(num);
                    } else {
                        int idx = Collections.binarySearch(list, num);
                        if (idx < 0) {
                            idx = -(idx + 1);
                        }
                        list.set(idx, num);
                    }
                }
            }
            max = Math.max(max, list.size());
        }

        return max;
    }
}