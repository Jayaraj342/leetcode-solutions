class Solution {
    String palindrome = "";

    public String longestPalindrome(String s) {
        for (int i = 0; i < s.length(); i++) {
            helper(s, i, i);
            helper(s, i, i + 1);
        }

        return palindrome;
    }

    private void helper(String s, int i, int j) {
        while (i >= 0 && j < s.length()) {
            if (s.charAt(i) != s.charAt(j)) {
                break;
            }
            i--;
            j++;
        }
        String curr = s.substring(i + 1, j);
        if (curr.length() > palindrome.length()) {
            palindrome = curr;
        }
    }
}

// Manacher's algorithm - https://www.youtube.com/watch?v=ei7qghJEj4Y
class Solution {
    public String longestPalindrome(String s) {
        String transformed = "#" + String.join("#", s.split("")) + "#";
        int n = transformed.length();

        int[] radius = new int[n];

        int center = 0, right = 0;
        int bestCenter = 0, bestRadius = 0;
        for (int i = 0; i < n; i++) {
            int mirror = 2 * center - i;

            if (i < right) {
                radius[i] = Math.min(right - i, radius[mirror]);
            }

            while (i - radius[i] - 1 >= 0 && i + radius[i] + 1 < n
                    && transformed.charAt(i - radius[i] - 1) == transformed.charAt(i + radius[i] + 1)
            ) {
                radius[i]++;
            }

            if (i + radius[i] > right) {
                center = i;
                right = i + radius[i];
            }

            if (radius[i] > bestRadius) {
                bestRadius = radius[i];
                bestCenter = i;
            }
        }

        int start = (bestCenter - bestRadius) / 2;
        return s.substring(start, start + bestRadius);
    }
}

// 131. Palindrome Partitioning https://leetcode.com/problems/palindrome-partitioning/