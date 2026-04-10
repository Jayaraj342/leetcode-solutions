class Solution {
    public int maxDistinct(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            set.add(c);
        }

        return set.size();
    }
}

class Solution {
    public int maxDistinct(String s) {
        int n = s.length();
        int lo = 1, hi = Math.min(26, n);
        int res = 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (canSplit(s, mid)) {
                res = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return res;
    }

    private boolean canSplit(String word, int target) {
        int splits = 0;
        boolean[] used = new boolean[26];
        for (char c : word.toCharArray()) {
            if (!used[c - 'a']) {
                splits++;
                used[c - 'a'] = true;
            }
        }

        return splits >= target;
    }
}