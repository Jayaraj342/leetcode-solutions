class Solution {
    public List<Integer> partitionLabels(String s) {
        // calc last occurrence of each char
        int[] lastIdx = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int curr = s.charAt(i);
            lastIdx[curr - 'a'] = i;
        }

        List<Integer> res = new ArrayList<>();
        int end = 0, size = 0;
        for (int i = 0; i < n; i++) {
            int curr = s.charAt(i);
            end = Math.max(end, lastIdx[curr - 'a']);
            size++;
            if (end == i) {
                res.add(size);
                size = 0;
            }
        }

        return res;
    }
}