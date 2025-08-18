// space O(1)
class Solution {
    public int countBinarySubstrings(String s) {
        int prevCount = 0, currCount = 1, res = 0;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                currCount++;
            } else {
                res += Math.min(prevCount, currCount);
                prevCount = currCount;
                currCount = 1;
            }
        }
        res += Math.min(prevCount, currCount);

        return res;
    }
}

// space O(n)
class Solution {
    public int countBinarySubstrings(String s) {
        List<Integer> groups = new ArrayList<>();
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                groups.add(count);
                count = 1;
            }
        }
        groups.add(count);

        int res = 0;
        for (int i = 1; i < groups.size(); i++) {
            res += Math.min(groups.get(i), groups.get(i - 1));
        }

        return res;
    }
}

// https://www.geeksforgeeks.org/substring-equal-number-0-1-2/

// mine
class Solution {
    public int countBinarySubstrings(String s) {
        int n = s.length();

        int count = 0, prev = 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                count++;
                prev = 3;
            } else if (prev > 0 && i - prev >= 0 && s.charAt(i) != s.charAt(i - prev)) {
                count++;
                prev += 2;
            } else {
                prev = 0;
            }
        }

        return count;
    }
}