// N * k
class Solution {
    // s = "wordgoodgoodgoodbestword"
    // words = ["word", "good", "best", "good"]
    public List<Integer> findSubstring(String s, String[] words) {
        int N = s.length(), n = words.length, k = words[0].length();

        Map<String, Integer> freq = new HashMap<>();
        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }

        List<Integer> res = new ArrayList<>();

        // From each offset, will do the sliding window
        for (int offset = 0; offset < k; offset++) {// since N != n * k => Have to consider all offsets
            int left = offset, cnt = 0;
            Map<String, Integer> seen = new HashMap<>();
            for (int right = offset; right <= N - k; right += k) {
                String curr = s.substring(right, right + k);
                if (freq.containsKey(curr)) {
                    seen.put(curr, seen.getOrDefault(curr, 0) + 1);
                    cnt++;

                    while (seen.get(curr) > freq.get(curr)) {
                        String leftStr = s.substring(left, left + k);
                        seen.put(leftStr, seen.get(leftStr) - 1);
                        left += k;
                        cnt--;
                    }

                    if (cnt == n) {
                        res.add(left);
                    }
                } else {
                    cnt = 0;
                    left = right + k;
                    seen.clear();
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        new Solution().findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"});
    }
}

// N * n * len => TLA
// SC : n
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> wordMap = new HashMap<>();
        for(String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        List<Integer> res = new ArrayList<>();
        int n = words.length, len = words[0].length();
        for (int i = 0; i <= s.length() - (n * len); i++) {
            if (dfs(s, i, len, new HashMap<>(wordMap))) {
                res.add(i);
            }
        }

        return res;
    }

    private boolean dfs(String str, int idx, int len, Map<String, Integer> words) {
        if (words.isEmpty()) {
            return true;
        }

        String curr = str.substring(idx, idx + len);
        if (words.containsKey(curr)) {
            int cnt = words.remove(curr);
            if (cnt > 1) {
                words.put(curr, cnt - 1);
            }
            return dfs(str, idx + len, len, words);
        }

        return false;
    }

    public static void main(String[] args) {
        new Solution().findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"});
    }
}