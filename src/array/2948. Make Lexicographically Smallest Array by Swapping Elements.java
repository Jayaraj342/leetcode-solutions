class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        // Sort and create mapping
        int[] sortedArr = Arrays.stream(nums).sorted().toArray();
        List<Deque<Integer>> groups = new ArrayList<>();
        Map<Integer, Integer> numGroupMap = new HashMap<>();

        for (int num : sortedArr) {
            if (groups.isEmpty() || num - groups.get(groups.size() - 1).peekLast() > limit) {
                groups.add(new ArrayDeque<>());
            }
            groups.get(groups.size() - 1).add(num);
            numGroupMap.put(num, groups.size() - 1);
        }

        // Build result array
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            Integer groupIndex = numGroupMap.get(nums[i]);
            res[i] = groups.get(groupIndex).remove();
        }

        return res;
    }
}