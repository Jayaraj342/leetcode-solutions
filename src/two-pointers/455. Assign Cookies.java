class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int m = g.length, n = s.length;
        int i = 0, j = 0, cnt = 0;
        while (i < m && j < n) {
            while (j < n && s[j] < g[i]) {
                j++;
            }
            if (j < n && g[i] <= s[j]) {
                i++;
                j++;
                cnt++;
            } else {
                i++;
            }
        }

        return cnt;
    }
}