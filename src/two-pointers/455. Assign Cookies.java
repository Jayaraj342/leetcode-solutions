class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int i = 0, j = 0;
        int m = g.length, n = s.length;

        // Use two pointers to assign cookies to children
        while (i < m && j < n) {
            if (s[j] >= g[i]) { // Cookie can satisfy this child
                i++; // Move to next child
            }
            j++; // Move to next cookie
        }

        return i; // Number of satisfied children
    }
}