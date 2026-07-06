// O(d * n)
class Solution {
    public void radixSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    private void countingSort(int[] arr, int exp) {
        int n = arr.length;

        int[] output = new int[n];
        int[] count = new int[10];

        // frequency
        for (int num : arr) {
            int digit = (num / exp) % 10;
            count[digit]++;
        }

        // prefix sum
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // stable placement
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    public static void main(String[] args) {
        new Solution().radixSort(new int[] {2, 24, 45, 66, 75, 90, 170, 802});
    }
}