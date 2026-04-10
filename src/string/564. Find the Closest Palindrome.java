class Solution {
    public String nearestPalindromic(String n) {
        int len = n.length();
        int i = len % 2 == 0 ? len / 2 - 1 : len / 2;
        List<Long> possibilities = getPossibilities(n, i, len);

        // Find the palindrome with minimum difference, and minimum value.
        long diff = Long.MAX_VALUE, res = 0, nl = Long.parseLong(n);
        for (long candidate : possibilities) {
            if (candidate == nl) continue;
            if (Math.abs(candidate - nl) < diff) {
                diff = Math.abs(candidate - nl);
                res = candidate;
            } else if (Math.abs(candidate - nl) == diff) {
                res = Math.min(res, candidate);
            }
        }

        return String.valueOf(res);
    }

    private List<Long> getPossibilities(String n, int i, int len) {
        long firstHalf = Long.parseLong(n.substring(0, i + 1));
        /*
        Generate possible palindromic candidates:
        1. Create a palindrome by mirroring the first half.
        2. Create a palindrome by mirroring the first half incremented by 1.
        3. Create a palindrome by mirroring the first half decremented by 1.
        4. Handle edge cases by considering palindromes of the form 999...
           and 100...001 (smallest and largest n-digit palindromes).
        */
        List<Long> possibilities = new ArrayList<>();

        possibilities.add(halfToPalindrome(firstHalf, len % 2 == 0));
        possibilities.add(halfToPalindrome(firstHalf + 1, len % 2 == 0));
        possibilities.add(halfToPalindrome(firstHalf - 1, len % 2 == 0));
        possibilities.add((long) Math.pow(10, len - 1) - 1);
        possibilities.add((long) Math.pow(10, len) + 1);

        return possibilities;
    }

    private long halfToPalindrome(long left, boolean even) {
        // Convert the given half to palindrome.
        long res = left;
        if (!even) left = left / 10;
        while (left > 0) {
            res = res * 10 + (left % 10);
            left /= 10;
        }
        return res;
    }
}