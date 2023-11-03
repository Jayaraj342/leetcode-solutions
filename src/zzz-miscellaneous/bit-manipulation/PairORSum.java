// https://www.geeksforgeeks.org/sum-of-bitwise-or-of-all-pairs-in-a-given-array/

class GFG {

    static int pairORSum(int[] arr, int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            // Count number of elements with the ith bit set(ie., 1)
            int k1 = 0;
            // Count number of elements with ith bit not-set(ie., 0) `
            int k0 = 0;

            for (int j = 0; j < n; j++) {
                if ((arr[j] & (1 << i)) != 0) {
                    k1++;
                }
                else {
                    k0++;
                }
            }
            // There are k1 set bits, means k1(k1-1)/2 pairs. k1C2
            // There are k0 not-set bits and k1 set bits so total pairs will be k1*k0.
            // Every pair adds 2^i to the answer. Therefore,

            ans = ans + (1 << i) * (k1 * (k1 - 1) / 2) + (1 << i) * (k1 * k0);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int n = arr.length;
        System.out.println(pairORSum(arr, n));
    }
}