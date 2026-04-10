// 3^4 => 81, 1
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        int n = s.length();

        // An IP address requires exactly 4 parts → min 4, max 12 digits
        if (n < 4 || n > 12) return result;

        String ans;
        for (int a = 1; a <= 3; a++) {
            for (int b = 1; b <= 3; b++) {
                for (int c = 1; c <= 3; c++) {
                    for (int d = 1; d <= 3; d++) {

                        // Check if lengths add up exactly
                        if (a + b + c + d == n) {
                            int A = Integer.parseInt(s.substring(0, a));
                            int B = Integer.parseInt(s.substring(a, a + b));
                            int C = Integer.parseInt(s.substring(a + b, a + b + c));
                            int D = Integer.parseInt(s.substring(a + b + c));

                            // Check range validity (0..255) and avoid leading zero issues
                            if (A <= 255 && B <= 255 && C <= 255 && D <= 255) {
                                ans = A + "." + B + "." + C + "." + D;

                                // Validate against leading zeros:
                                if (ans.length() == n + 3) {
                                    result.add(ans);
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}

// 3^4 => 81, 81
class Solution {
    private List<String> result = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        // An IP address has 4 parts, each 1 to 3 digits → total length must be between 4 and 12
        if (s.length() < 4 || s.length() > 12) return result;
        dfs(s, 0, 0, new StringBuilder());

        return result;
    }

    private void dfs(String s, int idx, int dots, StringBuilder current) {
        // If we placed 4 parts and used all chars → valid IP
        if (dots == 4 && idx == s.length()) {
            result.add(current.substring(0, current.length() - 1)); // remove last "."
            return;
        }

        // If parts exceed 4 or string consumed prematurely → stop
        if (dots >= 4) return;

        // Try segments of length 1 to 3
        for (int len = 1; len <= 3 && idx + len <= s.length(); len++) {
            String part = s.substring(idx, idx + len);

            // Skip numbers with leading zeros except "0" itself
            if (part.length() > 1 && part.charAt(0) == '0') break;

            int num = Integer.parseInt(part);
            if (num <= 255) {
                int beforeAppend = current.length();
                current.append(part).append(".");
                dfs(s, idx + len, dots + 1, current);
                current.setLength(beforeAppend); // backtrack
            }
        }
    }
}