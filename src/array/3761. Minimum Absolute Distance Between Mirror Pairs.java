class Solution {
    public int minMirrorPairDistance(int[] nums) {
        int n = nums.length;

        Map<Integer, Integer> indexMap = new HashMap<>();
        int min = n;
        for (int i = 0; i < n; i++) {
            int num = nums[i], rev = reverse(num);
            if (indexMap.containsKey(num)) {
                min = Math.min(min, i - indexMap.get(num));
            }
            indexMap.put(rev, i);
        }

        return min == n ? -1 : min;
    }

    private int reverse(int num) {
        int rev = 0;
        while (num > 0) {
            rev = (rev * 10) + (num % 10);
            num /= 10;
        }

        return rev;
    }
}

class Solution {
    public int minMirrorPairDistance(int[] nums) {
        int n = nums.length;

        Map<Integer, Integer> indexMap = new HashMap<>();
        int min = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            int num = nums[i], rev = reverse(num);
            if (indexMap.containsKey(rev)) {
                min = Math.min(min, indexMap.get(rev) - i);
            }
            indexMap.put(num, i);
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private int reverse(int num) {
        int rev = 0;
        while (num > 0) {
            rev = (rev * 10) + (num % 10);
            num /= 10;
        }

        return rev;
    }
}