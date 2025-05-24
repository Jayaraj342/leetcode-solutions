class Solution {
    public int longestSquareStreak(int[] nums) {
        Arrays.sort(nums);

        Map<Integer, Integer> map = new HashMap<>();
        int res = -1;
        for (int num : nums) {
            int root = (int) Math.sqrt(num);
            if (root * root == num) {
                map.put(num, map.getOrDefault(root, 0) + 1);
            } else {
                map.put(num, 1);
            }
            res = Math.max(res, map.get(num));
        }

        return res <= 1 ? -1 : res;
    }
}