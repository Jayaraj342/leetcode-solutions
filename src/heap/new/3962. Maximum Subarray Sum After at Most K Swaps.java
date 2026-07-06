// https://leetcode.com/problems/maximum-subarray-sum-after-at-most-k-swaps
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/heap/new/3960. Frequency Balance Subarray.java

// n^2.log(n)
class Solution {
    public long maxSum(int[] nums, int k) {
        int n = nums.length;

        long ans = Long.MIN_VALUE;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        TreeMap<Integer, Integer> candidates = new TreeMap<>();
        TreeMap<Integer, Integer> others = new TreeMap<>();

        for (int start = 0; start < n; start++) {
            candidates.clear();
            others.clear();

            for (int i = 0; i < Math.max(0, n - k); i++) {
                add(others, sorted[i]);
            }

            for (int i = Math.max(0, n - k); i < n; i++) {
                add(candidates, sorted[i]);
            }

            long currentSum = 0;
            for (int end = start; end < n; end++) {
                if (!others.isEmpty()) {
                    int val;

                    if (others.containsKey(nums[end])) {
                        val = nums[end];
                        remove(others, val);
                    } else {
                        val = others.lastKey();
                        remove(others, val);
                    }

                    add(candidates, val);
                }

                int largestCandidate = candidates.lastKey();
                currentSum += largestCandidate;
                remove(candidates, largestCandidate);

                ans = Math.max(ans, currentSum);
            }
        }

        return ans;
    }

    private void add(TreeMap<Integer, Integer> map, int val) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    private void remove(TreeMap<Integer, Integer> map, int val) {
        int freq = map.get(val);

        if (freq == 1) {
            map.remove(val);
        } else {
            map.put(val, freq - 1);
        }
    }
}

// n^3.log(n)
class Solution {
    public long maxSum(int[] nums, int k) {
        int n = nums.length;

        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        long ans = Long.MIN_VALUE;

        for (int l = 0; l < n; l++) {
            for (int r = l; r < n; r++) {

                List<Integer> inside = new ArrayList<>();
                List<Integer> outside = new ArrayList<>();

                for (int i = 0; i < n; i++) {
                    if (l <= i && i <= r) {
                        inside.add(nums[i]);
                    } else {
                        outside.add(nums[i]);
                    }
                }

                Collections.sort(inside); // ascending
                outside.sort(Collections.reverseOrder()); // descending

                long gain = 0;

                int maxSwapPairs = Math.min(
                        k,
                        Math.min(inside.size(), outside.size())
                );

                for (int i = 0; i < maxSwapPairs; i++) {
                    if (outside.get(i) > inside.get(i)) {
                        gain += (long) outside.get(i) - inside.get(i);
                    }
                }

                long subarraySum = prefix[r + 1] - prefix[l];

                ans = Math.max(ans, subarraySum + gain);
            }
        }

        return ans;
    }
}