class Solution {
    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        int[] netShifts = new int[n];
        for (int[] shift : shifts) {
            int start = shift[0], end = shift[1];
            netShifts[start] += shift[2] == 0 ? -1 : 1;
            if (end + 1 < n) {
                netShifts[end + 1] += shift[2] == 0 ? 1 : -1;
            }
        }

        char[] res = new char[n];
        for (int i = 0; i < n; i++) {
            if (i >= 1) {
                netShifts[i] += netShifts[i - 1];
            }

            int shift = netShifts[i] % 26;
            int oldChar = s.charAt(i) - 97; // 0 -> a, 25 -> z
            int newChar = (oldChar + shift + 26) % 26;
            res[i] = (char) (newChar + 97);
        }

        return new String(res);
    }

    public static void main(String[] args) {
        new Solution().shiftingLetters("dztz", new int[][]{{0,0,0},{1,1,1}});
    }
}