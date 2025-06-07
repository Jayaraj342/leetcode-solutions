class Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(); // diff(1's - 0's) -> idx
        map.put(0, -1);
        int max = 0, diff = 0;
        for (int i = 0; i < nums.length; i++) {
            diff += nums[i] == 0 ? -1 : 1;
            if (!map.containsKey(diff)) {
                map.put(diff, i);
            }
            max = Math.max(max, i - map.get(diff));
        }

        return max;
    }
}