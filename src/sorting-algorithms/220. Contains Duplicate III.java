// n, k
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        if (valueDiff < 0 || indexDiff <= 0) return false;

        Map<Long, Long> bucketMap = new HashMap<>();
        long size = valueDiff + 1;  // Bucket size

        for (int i = 0; i < nums.length; i++) {
            long num = nums[i];
            long bucketId = getBucketId(num, size);

            // Case 1: Same bucket => difference ≤ valueDiff automatically
            if (bucketMap.containsKey(bucketId)) {
                return true;
            }

            // Case 2: Check previous bucket (neighbor)
            if (bucketMap.containsKey(bucketId - 1) &&
                    Math.abs(num - bucketMap.get(bucketId - 1)) <= valueDiff) {
                return true;
            }

            // Case 3: Check next bucket (neighbor)
            if (bucketMap.containsKey(bucketId + 1) &&
                    Math.abs(num - bucketMap.get(bucketId + 1)) <= valueDiff) {
                return true;
            }

            // Add current number to its bucket
            bucketMap.put(bucketId, num);

            // Maintain sliding window of size indexDiff
            if (i >= indexDiff) {
                long oldBucketId = getBucketId(nums[i - indexDiff], size);
                bucketMap.remove(oldBucketId);
            }
        }

        return false;
    }

    // Helper function to map number -> bucket ID
    private long getBucketId(long num, long size) {
        // Handle negative numbers properly
        return num >= 0 ? num / size : ((num + 1) / size) - 1;
    }
}

// n.log(k), k
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Integer> set = new TreeSet<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i > indexDiff) {
                set.remove(nums[i - indexDiff - 1]);
            }

            int curr = nums[i];
            Integer floor = set.floor(curr), ceil = set.ceiling(curr);
            if (floor != null && Math.abs(floor - curr) <= valueDiff || ceil != null && Math.abs(ceil - curr) <= valueDiff) {
                return true;
            }
            set.add(curr);
        }

        return false;
    }
}