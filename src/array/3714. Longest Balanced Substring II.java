// n, n
// but TLE
class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int maxLen = 1;

        // --- Case 1: all continuous ones ---
        int len = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                len++;
            }
            else {
                maxLen = Math.max(maxLen, len);
                len = 1;
            }
        }
        maxLen = Math.max(maxLen, len);

        // --- Case 2: two distinct characters ---
        maxLen = Math.max(maxLen, calcDoublets(s, 'a', 'b'));
        maxLen = Math.max(maxLen, calcDoublets(s, 'b', 'c'));
        maxLen = Math.max(maxLen, calcDoublets(s, 'a', 'c'));

        // --- Case 3: all 3 have the same frequency ---
        Map<String, Integer> map = new HashMap<>();
        map.put("0,0", -1);

        int[] count = new int[3];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;

            int ab = count[0] - count[1];
            int ac = count[0] - count[2];
            String key = ab + "," + ac;

            if (map.containsKey(key)) {
                maxLen = Math.max(maxLen, i - map.get(key));
            } else {
                map.put(key, i);
            }
        }

        return maxLen;
    }

    private int calcDoublets(String s, char x, char y) {
        int n = s.length();
        int sum = 0, maxLen = 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == x) sum++;
            else if (c == y) sum--;
            else { // reset for other chars
                map.clear();
                map.put(0, i);
                sum = 0;
                continue;
            }

            if (map.containsKey(sum)) {
                maxLen = Math.max(maxLen, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }

        return maxLen;
    }
}