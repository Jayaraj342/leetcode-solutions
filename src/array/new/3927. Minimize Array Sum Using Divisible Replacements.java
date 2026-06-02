// https://leetcode.com/problems/minimize-array-sum-using-divisible-replacements
// https://github.com/Jayaraj342/leetcode-solutions/blob/master/src/array/new/3927. Minimize Array Sum Using Divisible Replacements.java

class Solution {
    public long minArraySum(int[] nums) {
        Set<Integer> present = new HashSet<>();

        for (int num : nums) {
            present.add(num);
        }

        long sum = 0;
        for (int num : nums) {
            int smallest = num;

            for (int d = 1; d * d <= num; d++) {
                if (num % d != 0) {
                    continue;
                }

                int other = num / d;
                if (present.contains(d)) {
                    smallest = d;
                    break; // can't get smaller than first valid divisor
                }

                if (present.contains(other)) {
                    smallest = Math.min(smallest, other);
                }
            }

            sum += smallest;
        }

        return sum;
    }
}