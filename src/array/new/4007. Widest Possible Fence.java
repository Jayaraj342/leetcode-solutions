// https://leetcode.com/problems/widest-possible-fence
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/array/new/4007. Widest Possible Fence.java

class Solution {
    public int maximumWidth(int[] planks) {
        Map<Integer, Integer> cnt = new HashMap<>();
        Map<Integer, Integer> res = new HashMap<>();

        for (int plank : planks) {
            cnt.put(plank, cnt.getOrDefault(plank, 0) + 1);
            res.put(plank, res.getOrDefault(plank, 0) + 1);
        }

        for (int a : cnt.keySet()) {
            for (int b : cnt.keySet()) {
                if (a < b) {
                    res.put(a + b, res.getOrDefault(a + b, 0) + Math.min(cnt.get(a), cnt.get(b)));
                }
                if (a == b) {
                    res.put(a + b, res.getOrDefault(a + b, 0) + cnt.get(a) / 2);
                }
            }
        }

        int max = 0;
        for (int num : res.values()) {
            max = Math.max(max, num);
        }

        return max;
    }
}