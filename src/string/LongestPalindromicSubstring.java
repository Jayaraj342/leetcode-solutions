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