// n.log(n), 1
class Solution {
    public int minimumK(int[] nums) {
        long sum = 0;
        for (int n : nums) {
            sum += n;
        }

        int lo = 1, hi = (int) Math.min(Integer.MAX_VALUE, sum);
        int ans = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (isValid(nums, mid)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }

    private boolean isValid(int[] nums, int k) {
        long required = 0;

        for (int n : nums) {
            // ceil(n / k)
            required += (n + k - 1) / k;

            if (required > (long) k * k) {
                return false;
            }
        }

        return required <= (long) k * k;
    }
}