// https://leetcode.com/problems/minimum-cost-to-equalize-arrays-using-swaps/
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/array/new/3868. Minimum Cost to Equalize Arrays Using Swaps.java

class Solution {
    public int minCost(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) map.merge(num, 1, Integer::sum);
        for (int num : nums2) map.merge(num, -1, Integer::sum);

        int res = 0;
        for (int val : map.values()) {
            if (val % 2 != 0) return -1;
            if (val > 0) res += val / 2;
        }

        return res;
    }
}

class Solution {
    public int minCost(int[] nums1, int[] nums2) {
        Map<Integer, Integer> freq1 = new HashMap<>();
        Map<Integer, Integer> freq2 = new HashMap<>();

        for (int num : nums1) {
            freq1.put(num, freq1.getOrDefault(num, 0) + 1);
        }

        for (int num : nums2) {
            if (freq1.getOrDefault(num, 0) >= 1) {
                int val = freq1.get(num) - 1;
                freq1.put(num, val);

                if (val == 0) {
                    freq1.remove(num);
                }
            } else {
                freq2.put(num, freq2.getOrDefault(num, 0) + 1);
            }
        }

        int cost = 0;

        Set<Integer> keys1 = new HashSet<>(freq1.keySet());
        for (int key : keys1) {
            int val = freq1.get(key);
            if (val % 2 != 0) {
                return -1;
            }

            cost += val / 2;
        }

        Set<Integer> keys2 = new HashSet<>(freq2.keySet());
        for (int key : keys2) {
            int val = freq2.get(key);
            if (val % 2 != 0) {
                return -1;
            }

            cost += val / 2;
        }

        return cost / 2;
    }
}