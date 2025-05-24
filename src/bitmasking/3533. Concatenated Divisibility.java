class Solution {
    private Map<String, List<Integer>> memo;
    private long[] pow10;

    public int[] concatenatedDivisibility(int[] nums, int k) {
        Arrays.sort(nums);

        int n = nums.length;
        memo = new HashMap<>();
        pow10 = new long[n];

        for (int i = 0; i < n; i++) {
            int len = String.valueOf(nums[i]).length();
            pow10[i] = (long) Math.pow(10, len);
        }

        List<Integer> result = dfs(nums, k, 0, 0);
        if (result == null) return new int[0];

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private List<Integer> dfs(int[] nums, int k, int mask, int rem) {
        int n = nums.length;

        if (mask == (1 << n) - 1) {// n 1's in the mask
            return rem == 0 ? new ArrayList<>() : null;
        }

        String key = mask + "," + rem;
        if (memo.containsKey(key)) return memo.get(key);

        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) != 0) continue;

            int newMask = mask | (1 << i);
            long nextRem = (rem * pow10[i] + nums[i]) % k;

            List<Integer> res = dfs(nums, k, newMask, (int) nextRem);
            if (res != null) {
                List<Integer> path = new ArrayList<>();
                path.add(nums[i]);
                path.addAll(res);
                memo.put(key, path);
                return path;
            }
        }

        memo.put(key, null);
        return null;
    }

    public static void main(String[] args) {
        int[] result = new Solution().concatenatedDivisibility(new int[]{2, 7}, 4);
        System.out.println(Arrays.toString(result));
    }
}
