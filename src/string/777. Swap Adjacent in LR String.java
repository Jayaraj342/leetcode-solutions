class Solution {
    public boolean canTransform(String start, String result) {
        // Remove 'X' and check relative order of 'L' and 'R'
        String s1 = start.replace("X", "");
        String s2 = result.replace("X", "");
        if (!s1.equals(s2)) return false;

        int i = 0, j = 0, n = start.length();

        while (i < n && j < n) {
            // Skip X's in both strings
            while (i < n && start.charAt(i) == 'X') i++;
            while (j < n && result.charAt(j) == 'X') j++;

            // If one pointer reaches the end, both must
            if (i == n || j == n) break;

            char c = start.charAt(i);
            if (c == 'R' && i > j) return false; // R can only move right
            if (c == 'L' && i < j) return false; // L can only move left

            i++;
            j++;
        }

        return true;
    }
}

class Solution {
    public boolean canTransform(String start, String result) {
        // If we see R -> X (R in start and X in result), cnt++;
        // Have to get one X -> R before we encounter L
        int n = start.length(), cnt = 0;
        for (int i = 0; i < n; i++) {
            char a = start.charAt(i), b = result.charAt(i);
            if (a == 'L' || b == 'L') {
                if (cnt != 0 || a == 'R' || b == 'R') {
                    return false;
                }
                continue;
            }
            if (a != b) {
                if (a == 'R') {
                    cnt++;
                } else {
                    cnt--;
                }
                if (cnt < 0) {
                    return false;
                }
            }
        }

        if (cnt != 0) {
            return false;
        }

        for (int i = n - 1; i >= 0; i--) {
            char a = start.charAt(i), b = result.charAt(i);
            if (a == 'R' || b == 'R') {
                if (cnt != 0 || a == 'L' || b == 'L') {
                    return false;
                }
                continue;
            }
            if (a != b) {
                if (a == 'L') {
                    cnt++;
                } else {
                    cnt--;
                }
                if (cnt < 0) {
                    return false;
                }
            }
        }

        return cnt == 0;
    }

    public static void main(String[] args) {
        new Solution().canTransform("RXXLRXRXL", "XRLXXRRLX");
    }
}