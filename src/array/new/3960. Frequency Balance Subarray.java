// https://leetcode.com/problems/frequency-balance-subarray/
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/array/new/3960. Frequency Balance Subarray.java
class Solution {
    public int getLength(int[] nums) {
        int n = nums.length;
        int max = 1;
        for (int start = 0; start < n; start++) {
            Map<Integer, Integer> cnt = new HashMap<>();
            Map<Integer, Integer> freq = new HashMap<>();

            for (int end = start; end < n; end++) {
                int num = nums[end];

                int oldFreq = cnt.getOrDefault(num, 0);
                int newFreq = oldFreq + 1;
                cnt.put(num, newFreq);

                freq.put(newFreq, freq.getOrDefault(newFreq, 0) + 1);
                if (oldFreq > 0) {
                    if (freq.get(oldFreq) == 1) {
                        freq.remove(oldFreq);
                    } else {
                        freq.put(oldFreq, freq.getOrDefault(oldFreq, 0) - 1);
                    }
                }

                int distFreq = freq.size();
                if (cnt.size() == 1 || (distFreq == 2 && (
                        newFreq % 2 == 0 && freq.containsKey(newFreq / 2) || freq.containsKey(newFreq * 2)
                )
                )) {
                    max = Math.max(max, end - start + 1);
                }
            }
        }

        return max;
    }
}