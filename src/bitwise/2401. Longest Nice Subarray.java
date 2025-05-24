class Solution {
    public int longestNiceSubarray(int[] nums) {
        int used = 0, j = 0, res = 0;
        for (int i = 0; i < nums.length; ++i) {
            while ((used & nums[i]) != 0) {
                used ^= nums[j++];
            }
            used |= nums[i];
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}

class Solution {
    public int longestNiceSubarray(int[] nums) {
        int n = nums.length;
        int lo = 1, hi = 32;
        int res = 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (canFormNiceSubarray(nums, mid)) {
                res = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return res;
    }

    private boolean canFormNiceSubarray(int[] nums, int len) {
        int n = nums.length;
        for (int i = 0; i < n - len + 1; i++) {
            int j = i, xor = 0;
            while (j < i + len) {
                if ((xor & nums[j]) != 0) {// all set bits are in unique position
                    break;
                }
                xor ^= nums[j++];
            }
            if (j == i + len) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        new Solution().longestNiceSubarray(new int[]{1, 3, 8, 48, 10});
    }
}