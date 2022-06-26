// https://www.geeksforgeeks.org/sum-of-width-max-and-min-diff-of-all-subsequences/
class GFG {
    static int MOD = 1000000007;

    static int subseqWidths(int[] arr, int n) {
        Arrays.sort(arr);

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = (ans + ((int) Math.pow(2, i) - (int) Math.pow(2, n - 1 - i)) * arr[i]) % MOD;
            // 2^0 * 1 - 2^2 * 1 + 2^2 * 3 - 2^0 * 3 = 6
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3};
        int n = arr.length;
        System.out.println(subseqWidths(arr, n));
    }
}