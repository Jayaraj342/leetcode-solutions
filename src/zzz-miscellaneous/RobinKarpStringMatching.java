class RobinKarpStringMatching {
    static void search(String pat, String txt, int prime) {
        int m = pat.length();
        int n = txt.length();
        int i, j;
        int patHash = 0;
        int txtHash = 0;
        int h = 1;

        // The value of h would be "pow(10, M-1)%q"
        for (i = 0; i < m - 1; i++) {
            h = (h * 10) % prime;
        }

        // Calculate the hash value of pattern and first
        // window of text
        for (i = 0; i < m; i++) {
            patHash = (10 * patHash + pat.charAt(i)) % prime;
            txtHash = (10 * txtHash + txt.charAt(i)) % prime;
        }

        for (i = 0; i <= n - m; i++) {
            // Check the hash values of current window of text
            // and pattern. If the hash values match then only
            // check for characters one by one
            if (patHash == txtHash) {
                for (j = 0; j < m; j++) {
                    if (txt.charAt(i + j) != pat.charAt(j))
                        break;
                }
                if (j == m) {
                    System.out.println("Pattern found at index " + i);
                }
            }

            if (i < n - m) {
                // since we mod by prime, m-1 times we just instead of multiplying by 10^m-1 we multiply by 10^m-1 % prime
                txtHash = (10 * (txtHash - txt.charAt(i) * h) + txt.charAt(i + m)) % prime;

                if (txtHash < 0) {
                    txtHash = (txtHash + prime);
                }
            }
        }
    }

    public static void main(String[] args) {
        String txt = "abababc";
        String pat = "abc";

        int q = 101;
        search(pat, txt, q);
    }
}