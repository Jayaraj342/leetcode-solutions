class Solution {
    public boolean isItPossible(String word1, String word2) {
        int[] count1 = new int[26];
        int[] count2 = new int[26];

        for (char c : word1.toCharArray()) count1[c - 'a']++;
        for (char c : word2.toCharArray()) count2[c - 'a']++;

        int dist1 = 0, dist2 = 0;
        for (int i = 0; i < 26; i++) {
            if (count1[i] > 0) dist1++;
            if (count2[i] > 0) dist2++;
        }

        if (dist1 == dist2 && word1.length() == word2.length()) return true;

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (i != j && count1[i] != 0 && count2[j] != 0) {
                    int tempDist1 = dist1, tempDist2 = dist2;

                    if (count1[j] == 0) tempDist1++;
                    if (count1[i] == 1) tempDist1--;

                    if (count2[i] == 0) tempDist2++;
                    if (count2[j] == 1) tempDist2--;

                    if (tempDist1 == tempDist2) return true;
                }
            }
        }

        return false;
    }
}