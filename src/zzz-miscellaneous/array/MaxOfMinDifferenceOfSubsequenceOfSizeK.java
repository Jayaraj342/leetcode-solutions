// https://www.geeksforgeeks.org/maximum-of-minimum-difference-of-all-pairs-from-subsequences-of-given-size/
class MaxOfMinDifferenceOfSubsequenceOfSizeK {
    static boolean canFormSubsequenceWithDiff(int[] arr, int n, int k, int diff) {
        int count = 1;
        int last_position = arr[0];

        // If a subsequence of size k
        // with min diff = mid is possible
        // return true else false
        for (int i = 1; i < n; i++) {
            if (arr[i] - last_position >= diff) {
                last_position = arr[i];
                count++;
                if (count == k) {
                    return true;
                }
            }
        }
        return false;
    }

    static int findMinDifference(int[] arr, int n, int k) {
        Arrays.sort(arr);

        // Stores the boundaries
        // of the search space [0, 4] in this case
        int lo = 0;
        int hi = arr[n - 1] - arr[0];

        int ans = 0;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            // If subsequence can be formed
            // with min diff mid and size k
            if (canFormSubsequenceWithDiff(arr, n, k, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5};
        int n = arr.length;
        int k = 3;

        int min_difference = findMinDifference(arr, n, k);
        System.out.print(min_difference);
    }
}