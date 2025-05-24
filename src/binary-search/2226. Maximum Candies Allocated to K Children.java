class Solution {
    public int maximumCandies(int[] candies, long k) {
        int maxCandy = 0;
        for (int candy : candies) {
            maxCandy = Math.max(maxCandy, candy);
        }

        int lo = 1, hi = maxCandy;
        int maxCandySize = 0;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (canDistribute(candies, mid, k)) {
                maxCandySize = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return maxCandySize;
    }

    private boolean canDistribute(int[] candies, int size, long k) {
        long totalChildren = 0;
        for (int candy : candies) {
            totalChildren += candy / size;
            if (totalChildren >= k) return true; // Early exit optimization
        }

        return false;
    }
}

// won't work for candies = [4,7,5] k = 4
class Solution {
    public int maximumCandies(int[] candies, long k) {
        int min = Integer.MAX_VALUE, sum = 0;
        for (int num : candies) {
            min = Math.min(min, num);
            sum += num;
        }

        for (int i = min; i >= 0; i--) {
            if (i * k <= sum) {
                return i;
            }
        }

        return 0;
    }
}