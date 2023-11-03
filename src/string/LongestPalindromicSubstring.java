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

class Solution {
    public String longestPalindrome(String s) {
        int longest = 0;
        int idx = 0;

        int n = s.length();
        for (int i = 0; i < n; i++) {
            int l = i, r = i;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                if (r - l + 1 > longest) {
                    longest = r - l + 1;
                    idx = l;
                }
                l--;
                r++;
            }

            l = i;
            r = i + 1;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                if (r - l + 1 > longest) {
                    longest = r - l + 1;
                    idx = l;
                }
                l--;
                r++;
            }
        }

        return s.substring(idx, idx + longest);
    }
}