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