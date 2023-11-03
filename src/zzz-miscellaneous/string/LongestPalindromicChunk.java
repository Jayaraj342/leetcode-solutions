class LongestPalindromicChunk {
    private static int LPCRec(String curr_str, int count, int len, String str) {
        if (curr_str == null || curr_str.isEmpty())
            return 0;

        if (curr_str.length() == 1) {
            return count + 1;
        }

        int n = curr_str.length();
        for (int i = 0; i < n / 2; i++) {
            if (curr_str.substring(0, i + 1).equals(curr_str.substring(n - 1 - i, n))) {
                return LPCRec(curr_str.substring(i + 1, n - 1 - i), count + 2, len + (i + 1) * 2, str);
            }
        }

        return count + 1;
    }

    public static int LPC(String str) {
        return LPCRec(str, 0, 0, str);
    }

    public static void main(String[] args) {
        System.out.println("V : " + LPC("V"));
        System.out.println("VOLVO : " + LPC("VOLVO"));
        System.out.println("VOLVOV : " + LPC("VOLVOV"));
        System.out.println("ghiabcdefhelloadamhelloabcdefghi : " + LPC("ghiabcdefhelloadamhelloabcdefghi"));

        System.out.println("ghiabcdefhelloadamhelloabcdefghik : " + LPC("ghiabcdefhelloadamhelloabcdefghik"));

        System.out.println("antaprezatepzapreanta : " + LPC("antaprezatepzapreanta"));
    }
}
