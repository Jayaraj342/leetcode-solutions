class Solution {
    public int countBinaryPalindromes(long n) {
        if (n == 0) return 1;

        int res = 1;
        // Convert to binary string
        String binary = Long.toBinaryString(n);
        int len = binary.length();

        // Step 1: Count all binary palindromes with fewer bits than n
        for (int k = 1; k < len; k++) {
            res += 1 << ((k + 1) / 2 - 1);
        }

        // Step 2: Handle same-bit-length palindromes
        int half = (len + 1) / 2;
        for (int i = 1; i < half; i++) {
            if (binary.charAt(i) == '1') {
                res += 1 << (half - i - 1);
            }
        }

        // Step 3: Check if n itself forms a palindrome
        String leftHalf = binary.substring(0, half);
        String rightHalf = binary.substring(len - half);
        String reversedLeft = new StringBuilder(leftHalf).reverse().toString();

        if (reversedLeft.compareTo(rightHalf) <= 0) {
            res++;
        }

        return res;
    }
}