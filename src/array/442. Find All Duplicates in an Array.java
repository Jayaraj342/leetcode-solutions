class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int num : nums) {
            num = Math.abs(num);
            if (nums[num - 1] < 0) {
                res.add(num);
            }
            nums[num - 1] = -nums[num - 1];
        }

        return res;
    }
}