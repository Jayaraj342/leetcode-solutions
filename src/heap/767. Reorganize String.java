class Solution {
    public String reorganizeString(String s) {
        // Count the frequency of each letter
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Find the character with the highest frequency
        int maxCount = 0;
        int maxCharacter = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] > maxCount) {
                maxCount = freq[i];
                maxCharacter = i;
            }
        }

        // If the most frequent char appears too often, impossible to arrange
        if (maxCount > (s.length() + 1) / 2) {
            return "";
        }

        char[] res = new char[s.length()];
        int index = 0;
        // Place the most frequent character at even indices first
        while (freq[maxCharacter]-- > 0) {
            res[index] = (char) ('a' + maxCharacter);
            index += 2;
        }

        // Fill remaining characters
        for (int c = 0; c < 26; c++) {
            while (freq[c]-- > 0) {
                if (index >= s.length()) {
                    index = 1; // wrap to odd positions
                }
                res[index] = (char) ('a' + c);
                index += 2;
            }
        }

        return new String(res);
    }
}

class Solution {
    public String reorganizeString(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((a, b) -> b.cnt - a.cnt);
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                maxHeap.add(new Pair((char) ('a' + i), freq[i]));
            }
        }

        StringBuilder res = new StringBuilder();
        while (maxHeap.size() > 1) {
            Pair first = maxHeap.remove();
            Pair second = maxHeap.remove();

            res.append(first.c);
            res.append(second.c);

            if (--first.cnt > 0) maxHeap.add(first);
            if (--second.cnt > 0) maxHeap.add(second);
        }

        if (!maxHeap.isEmpty()) {
            Pair last = maxHeap.remove();
            if (last.cnt > 1) {// impossible to reorganize
                return "";
            }
            res.append(last.c);
        }

        return res.toString();
    }

    static class Pair {
        char c;
        int cnt;

        Pair(char c, int cnt) {
            this.c = c;
            this.cnt = cnt;
        }
    }
}