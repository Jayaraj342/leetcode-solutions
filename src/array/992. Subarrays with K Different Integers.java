class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        Map<Integer, Integer> cnts = new HashMap<>();
        int lf = 0, ln = 0, res = 0, n = nums.length;
        for (int curr : nums) {
            cnts.put(curr, cnts.getOrDefault(curr, 0) + 1);

            while (cnts.size() > k) {
                int cnt = cnts.get(nums[ln]) - 1;
                cnts.put(nums[ln], cnt);
                if (cnt == 0) {
                    cnts.remove(nums[ln]);
                }
                ln++;
                lf = ln;
            }

            while (cnts.get(nums[ln]) > 1) {
                cnts.put(nums[ln], cnts.get(nums[ln]) - 1);
                ln++;
            }

            if (cnts.size() == k) {
                res += ln - lf + 1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        new Solution().subarraysWithKDistinct(new int[]{1, 2, 1, 2, 3}, 2);
    }
}