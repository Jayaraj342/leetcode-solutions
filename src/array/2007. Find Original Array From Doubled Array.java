class Solution {
    public int[] findOriginalArray(int[] changed) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : changed) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        Arrays.sort(changed);

        int n = changed.length;
        if (n % 2 != 0 || (freq.containsKey(0) && freq.get(0) % 2 != 0)) {
            return new int[] {};
        }

        int[] res = new int[n / 2];
        int i = 0;
        for (int num : changed) {
            if (freq.get(num) > 0 && freq.getOrDefault(2 * num, 0) > 0) {
                res[i++] = num;
                freq.put(num, freq.get(num) - 1);
                freq.put(2 * num, freq.get(2 * num) - 1);
            }
        }

        return i == res.length ? res : new int[] {};
    }
}