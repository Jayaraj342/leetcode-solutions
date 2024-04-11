class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        List<List<Integer>> ldss = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ldss.add(new ArrayList<>());
        }

        List<Integer> res = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            List<Integer> currMax = new ArrayList<>();
            currMax.add(nums[i]);
            for (int j = i + 1; j < n; j++) {
                if (nums[j] % nums[i] == 0) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.addAll(ldss.get(j));
                    if (temp.size() > currMax.size()) {
                        currMax = temp;
                    }
                }
            }

            ldss.set(i, currMax);
            if (currMax.size() > res.size()) {
                res = currMax;
            }
        }

        return res;
    }
}

class Solution {
    Map<Integer, List<Integer>> memo;

    public List<Integer> largestDivisibleSubset(int[] nums) {
        memo = new HashMap<>();
        Arrays.sort(nums);

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> curr = dfs(nums, i);
            if (curr.size() > res.size()) {
                res = curr;
            }
        }
        return res;
    }

    private List<Integer> dfs(int[] nums, int i) {
        if (i == nums.length) {
            return new ArrayList<>();
            a
        }
        if (memo.containsKey(i)) {
            return memo.get(i);
        }

        List<Integer> res = new ArrayList<>();
        res.add(nums[i]);
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] % nums[i] == 0) {
                // won't be O(n) as 2 ^ whatever, can be done only max 32 times, it doesn't depend on n
                List<Integer> temp = new ArrayList<>(dfs(nums, j));
                temp.add(nums[i]);
                if (temp.size() > res.size()) {
                    res = temp;
                }
            }
        }
        memo.put(i, res);

        return res;
    }
}