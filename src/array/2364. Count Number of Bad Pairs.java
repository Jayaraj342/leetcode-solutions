class Solution {
    public long countBadPairs(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> diffMap = new HashMap<>();

        // Count frequency of (num - index) values
        for (int i = 0; i < n; i++) {
            diffMap.put(nums[i] - i, diffMap.getOrDefault(nums[i] - i, 0) + 1);
        }

        long goodPairs = 0;
        // Calculate number of good pairs using combination formula
        for (int count : diffMap.values()) {
            goodPairs += (long) count * (count - 1) / 2;
        }

        // Total pairs - good pairs = bad pairs
        return (long) n * (n - 1) / 2 - goodPairs;
    }
}