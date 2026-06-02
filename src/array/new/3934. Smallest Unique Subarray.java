// https://leetcode.com/problems/smallest-unique-subarray/description/
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/array/new/3934. Smallest Unique Subarray.java
// Robin Karp - use double hashed ideally (Test fails for base = 31) => Ideally use 31 & 37
class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int lo = 1, hi = n;
        int res = n;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (hasUniqueSubarray(nums, mid)) {
                res = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return res;
    }

    public static boolean hasUniqueSubarray(int[] arr, int k) {
        // Base and MOD for the hash function
        long base = 215215;
        long MOD = 1_000_000_007;

        // Compute base^(k-1) % MOD for rolling hash eviction
        long basePow = 1;
        for (int i = 0; i < k - 1; i++) {
            basePow = (basePow * base) % MOD;
        }

        Map<Long, Integer> hashCounts = new HashMap<>();
        long currentHash = 0;

        // Compute hash for the first window
        for (int i = 0; i < k; i++) {
            currentHash = (currentHash * base + arr[i]) % MOD;
            if (currentHash < 0) currentHash += MOD;
        }
        hashCounts.put(currentHash, 1);

        // Slide the window across the rest of the array
        for (int i = k; i < arr.length; i++) {
            // Remove the leftmost element
            long toRemove = (arr[i - k] * basePow) % MOD;
            currentHash = (currentHash - toRemove + MOD) % MOD;

            // Add the rightmost element
            currentHash = (currentHash * base + arr[i]) % MOD;
            if (currentHash < 0) currentHash += MOD;

            hashCounts.put(currentHash, hashCounts.getOrDefault(currentHash, 0) + 1);
        }

        // Check if any hash appeared exactly once
        for (int count : hashCounts.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}