class KMPStringMatching {
    public void searchPattern(String pat, String txt) {
        int m = pat.length();
        int n = txt.length();

        // create lps[] that will hold the longest prefix suffix values for pattern
        int[] lps = computeLPSArray(pat.toCharArray(), m);

        int j = 0; // index for pat[]
        int i = 0; // index for txt[]
        while (i < n) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }

            if (j == m) {
                System.out.println("Found pattern at index " + (i - j));
                j = lps[j - 1];
            }
            // mismatch after j matches
            else if (i < n && pat.charAt(j) != txt.charAt(i)) {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
    }

    private int[] computeLPSArray(char[] pat, int m) {
        int[] lps = new int[m];
        int len = 0, i = 1;// as lps[0] is always 0
        while (i < m) {
            if (pat[i] == pat[len]) {
                len++;
                lps[i] = len;
                i++;
            } else {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0) {
                    len = lps[len - 1];
                    // Also, note that we do not increment
                    // i here
                } else {
                    // if (len == 0)
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    public static void main(String[] args) {
        String txt = "ababcabababd";
        String pat = "ababd";
        new KMPStringMatching().searchPattern(pat, txt);
    }
}