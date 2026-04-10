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
