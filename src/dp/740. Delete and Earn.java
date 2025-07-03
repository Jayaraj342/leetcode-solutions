// TC : O(n.logn), SC : O(n) - can be 1 if freq is not used
class Solution {
    public int deleteAndEarn(int[] nums) {
        Arrays.sort(nums);

        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            if (freq.containsKey(num)) {
                freq.put(num, freq.get(num) + 1);
            } else {
                list.add(num);
                freq.put(num, 1);
            }
        }

        int n = list.size(), val1 = 0, val2 = 0;
        for (int i = 0; i < n; i++) {
            int val = list.get(i) * freq.get(list.get(i));
            if (i > 0 && list.get(i - 1) == list.get(i) - 1) {
                int newVal2 = Math.max(val1 + val, val2), newVal1 = val2;
                val1 = newVal1;
                val2 = newVal2;
            } else {
                val1 = val2;
                val2 = val2 + val;
            }
        }

        return val2;
    }
}

// TC : O(n.logn), SC : O(n)
class Solution {
    int[] memo;

    public int deleteAndEarn(int[] nums) {
        memo = new int[nums.length];

        Arrays.sort(nums);
        return deleteAndEarn(nums, 0);
    }

    private int deleteAndEarn(int[] nums, int idx) {
        if (idx == nums.length) {
            return 0;
        }

        if (memo[idx] == 0) {
            int with = nums[idx];
            int skip = idx + 1;
            // skip same nums
            while (skip < nums.length && nums[skip] == nums[idx]) {
                with += nums[idx];
                skip++;
            }
            // skip next nums
            while (skip < nums.length && nums[skip] == nums[idx] + 1) {
                skip++;
            }

            with += deleteAndEarn(nums, skip);

            memo[idx] = Math.max(with, deleteAndEarn(nums, idx + 1));
        }

        return memo[idx];
    }
}