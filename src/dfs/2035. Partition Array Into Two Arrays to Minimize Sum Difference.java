// 2^(n/2)
class Solution {
    public int minimumDifference(int[] nums) {
        int n = nums.length;
        if (n == 2) return Math.abs(nums[1] - nums[0]);

        List<Integer>[] list1 = generate(nums, 0, n / 2);
        List<Integer>[] list2 = generate(nums, n / 2, n);

        int sum = Arrays.stream(nums).sum();
        int res = Integer.MAX_VALUE;

        for (int i = 0; i <= n / 2; i++) { // take i elements from the 1st set
            int k = n / 2 - i;// take k elements from the 2nd set

            List<Integer> left = list1[i], right = list2[k];
            int a = 0, b = right.size() - 1;
            while (a < left.size() && b >= 0) {
                int curr = left.get(a) + right.get(b);
                int diff = sum - 2 * curr;
                res = Math.min(res, Math.abs(diff));
                if (diff < 0) {// (as 2 * curr is still big, reduce it)
                    b--;
                } else if (diff > 0) {
                    a++;
                } else {
                    return 0;
                }
            }
        }

        return res;
    }

    private static List<Integer>[] generate(int[] nums, int start, int end) {
        int n = end - start;
        List<Integer>[] subsets = new List[n + 1];

        for (int mask = 0; mask < (1 << n); mask++) {
            int setBits = Integer.bitCount(mask);
            int sum = 0;
            for (int bit = 0; bit < n; bit++) {
                if (((mask >> bit) & 1) == 1) {
                    sum += nums[start + bit]; // include start offset
                }
            }
            if (subsets[setBits] == null) {
                subsets[setBits] = new ArrayList<>();
            }
            subsets[setBits].add(sum);
        }

        for (List<Integer> list : subsets) {
            Collections.sort(list);
        }

        return subsets;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minimumDifference(new int[]{3, 9, 7, 3})); // Expected 2
    }
}

// n * 2^(n/2)
// m can be neglected (length of array)
class Solution {
    public int minimumDifference(int[] nums) {
        int n = nums.length;
        if (n == 2) return Math.abs(nums[1] - nums[0]);

        TreeSet<Integer>[] list1 = generate(nums, 0, n / 2);
        TreeSet<Integer>[] list2 = generate(nums, n / 2, n);

        int sum = Arrays.stream(nums).sum();
        int halfSum = sum / 2;
        int res = Integer.MAX_VALUE;

        for (int i = 0; i <= n / 2; i++) { // take i elements from the 1st set
            int k = n / 2 - i;// take k elements from the 2nd set

            for (int num : list1[i]) {
                int target = halfSum - num;

                // Closest value >= target
                Integer ceil = list2[k].ceiling(target);
                if (ceil != null) {
                    res = Math.min(res, Math.abs(sum - 2 * (num + ceil)));
                    if (res == 0) return 0; // early exit
                }
            }
        }

        return res;
    }

    private static TreeSet<Integer>[] generate(int[] nums, int start, int end) {
        int n = end - start;
        TreeSet<Integer>[] subsets = new TreeSet[n + 1];

        for (int mask = 0; mask < (1 << n); mask++) {
            int setBits = Integer.bitCount(mask);
            int sum = 0;
            for (int bit = 0; bit < n; bit++) {
                if (((mask >> bit) & 1) == 1) {
                    sum += nums[start + bit]; // include start offset (for the 2nd set)
                }
            }
            if (subsets[setBits] == null) {
                subsets[setBits] = new TreeSet<>();
            }
            subsets[setBits].add(sum);
        }

        return subsets;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minimumDifference(new int[]{3, 9, 7, 3})); // Expected 2
    }
}

// Timeout : 2^N
class Solution {
    public int minimumDifference(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        int min = Integer.MAX_VALUE;
        int range = (int) Math.pow(2, n) - 1;
        for (int i = 1; i < range; i++) {
            if (Integer.bitCount(i) != n / 2) {
                continue;
            }
            int ones = 0;
            for (int bit = 0; bit < n; bit++) {
                if (((i >> bit) & 1) == 1) {
                    ones += nums[bit];
                }
            }

            int zeros = sum - ones;
            if (ones == zeros) {
                System.out.println("Hello");
            }
            min = Math.min(min, Math.abs(ones - zeros));
        }

        return min;
    }

    public static void main(String[] args) {
        new Solution().minimumDifference(new int[]{76, 8, 45, 20, 74, 84, 28, 1});
    }
}