class Solution {
    public int countPalindromicSubsequence(String s) {
        Set<Character> left = new HashSet<>();
        Map<Character, Integer> right = new HashMap<>();
        for (char c : s.toCharArray()) {
            right.put(c, right.getOrDefault(c, 0) + 1);
        }

        Set<String> res = new HashSet<>();
        for (char curr : s.toCharArray()) {
            int oldCnt = right.get(curr);
            if (oldCnt > 1) {
                right.put(curr, oldCnt - 1);
            } else {
                right.remove(curr);
            }

            for (char c : left) {
                if (right.containsKey(c)) {
                    res.add(c + "" + curr + "" + c);
                }
            }
            left.add(curr);
        }

        return res.size();
    }
}