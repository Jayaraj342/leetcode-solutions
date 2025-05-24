class Solution {
    // easy to calculate substrings occuring atleast k times - than exactly k times
    public long countOfSubstrings(String word, int k) {
        return countAtLeastK(word, k) - countAtLeastK(word, k + 1);
    }

    private long countAtLeastK(String word, int k) {
        int n = word.length();
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        Map<Character, Integer> vowelCount = new HashMap<>();

        int left = 0, consonantCount = 0;
        long res = 0;

        for (int right = 0; right < n; right++) {
            char currChar = word.charAt(right);

            if (vowels.contains(currChar)) {
                vowelCount.put(currChar, vowelCount.getOrDefault(currChar, 0) + 1);
            } else {
                consonantCount++;
            }

            // Maintain the window with at least 'k' consonants and all vowels present
            while (consonantCount >= k && vowelCount.size() == 5) {
                res += n - right; // Substrings starting from 'left' to 'right' till end are valid

                char leftChar = word.charAt(left);
                if (vowels.contains(leftChar)) {
                    vowelCount.put(leftChar, vowelCount.get(leftChar) - 1);
                    if (vowelCount.get(leftChar) == 0) {
                        vowelCount.remove(leftChar);
                    }
                } else {
                    consonantCount--;
                }
                left++;
            }
        }

        return res;
    }
}
