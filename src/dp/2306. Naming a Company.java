class Solution {
    public long distinctNames(String[] ideas) {
        Set<String>[] arr = new Set[26];
        for (int i = 0; i < 26; i++) {
            arr[i] = new HashSet<>();
        }

        for (String word : ideas) {
            char c = word.charAt(0);
            arr[c - 'a'].add(word.substring(1));
        }

        long res = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = i + 1; j < 26; j++) {
                long c1 = 0, c2 = 0;
                for (String word : arr[i]) {
                    if (!arr[j].contains(word)) {
                        c1++;
                    }
                }
                for (String word : arr[j]) {
                    if (!arr[i].contains(word)) {
                        c2++;
                    }
                }
                res += c1 * c2;
            }
        }

        return res * 2;
    }
}