class Solution {
    public int nextGreaterElement(int n) {
        char[] digits = String.valueOf(n).toCharArray();
        int i = digits.length - 2;

        // Find the first decreasing digit from the end
        while (i >= 0 && digits[i] >= digits[i + 1]) {
            i--;
        }

        // If no such digit is found, no greater number is possible
        if (i < 0) {
            return -1;
        }

        // Find the smallest digit greater than digits[i] to the right
        int j = digits.length - 1;
        while (digits[j] <= digits[i]) {
            j--;
        }

        // Swap them
        swap(digits, i, j);

        // Reverse the suffix
        reverse(digits, i + 1, digits.length - 1);

        long result = Long.parseLong(new String(digits));
        return result > Integer.MAX_VALUE ? -1 : (int) result;
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void reverse(char[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start++, end--);
        }
    }
}