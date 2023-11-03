// https://practice.geeksforgeeks.org/problems/find-sum-of-different-corresponding-bits-for-all-pairs4652/1
class GFG {

    static int sumBitDiff(int[] arr) {
        int diff = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                // XOR toggles the bits and will form a number that will have
                // set bits at the places where the numbers bits differ
                // eg: 010 ^ 111 = 101...diff of bits = count of 1's = 2

                int xor = arr[i] ^ arr[j];
                int count = countSetBits(xor); // Integer.bitCount(xor) can also be used

                // 2 * count, because (1,2) and (2, 1) - should count both instances
                diff += 2 * count;
            }
        }

        return diff;
    }

    //Kernighan algorithm
    // for n = 15 i.e. 1111 n-1=1110
    // &-> 1110 n-1=1101
    // &-> 1100 n-1=1011
    // &-> 1000 n-1=0111
    // &-> 0000 so, count = 4
    static int countSetBits(int n) {
        int count = 0;         // `count` stores the total bits set in `n`

        while (n != 0) {
            n = n & (n - 1); // clear the least significant bit set
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        int[] arr = {5, 10};
        int ans = sumBitDiff(arr);
        System.out.println(ans);
    }
}

// O(n)
class Solution {
    static int countBits(int n, long[] arr) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int currPos = 1 << i;
            int ones = 0;
            for (long num : arr) {
                if ((num & currPos) != 0) {
                    ones++;
                }
            }

            res += (ones * (n - ones) * 2);
        }

        return res;
    }
}