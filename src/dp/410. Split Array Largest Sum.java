class Solution {
    public int splitArray(int[] nums, int k) {
        int lo = 0, hi = 0;
        for(int num : nums) {
            lo = Math.max(lo, num);
            hi += num;
        }

        int ans = hi;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(isPossibleToSplit(nums, mid, k)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }

    private boolean isPossibleToSplit(int[] nums, int max, int k) {
        int currSum = 0, cnt = 0;;
        for(int num : nums) {
            currSum += num;
            if(currSum > max) {
                currSum = num;
                cnt ++;
            }
        }

        return cnt + 1 <= k;
    }
}