class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMostKDistinct(nums, k) - atMostKDistinct(nums, k - 1);
    }

    private int atMostKDistinct(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int lo = 0, cnt = 0;
        for (int hi = 0; hi < nums.length; hi++) {
            int curr = nums[hi];
            freqMap.put(curr, freqMap.getOrDefault(curr, 0) + 1);

            // Shrink window if more than k distinct elements
            while (freqMap.size() > k) {
                int leftNum = nums[lo];
                freqMap.put(leftNum, freqMap.get(leftNum) - 1);
                if (freqMap.get(leftNum) == 0) {
                    freqMap.remove(leftNum);
                }
                lo++;
            }

            cnt += hi - lo + 1;
        }

        return cnt;
    }
}

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