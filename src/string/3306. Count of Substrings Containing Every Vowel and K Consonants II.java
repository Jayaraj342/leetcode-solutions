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

class Solution {
    public boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    public long countOfSubstrings(String word, int k) {
        int n = word.length();
        Map<Character, Integer> vowels = new HashMap<>();
        int consonantCount = 0;
        long result = 0;

        // Precompute next consonant positions
        int[] nextConsonant = new int[n];
        int lastConsonant = n;
        for (int i = n - 1; i >= 0; i--) {
            nextConsonant[i] = lastConsonant;
            if (!isVowel(word.charAt(i))) {
                lastConsonant = i;
            }
        }

        // Sliding window
        int left = 0;
        for (int right = 0; right < n; right++) {
            char rightChar = word.charAt(right);
            if (isVowel(rightChar)) {
                vowels.put(rightChar, vowels.getOrDefault(rightChar, 0) + 1);
            } else {
                consonantCount++;
            }

            // Shrink window if too many consonants
            while (left <= right && consonantCount > k) {
                char leftChar = word.charAt(left);
                if (isVowel(leftChar)) {
                    vowels.put(leftChar, vowels.get(leftChar) - 1);
                    if (vowels.get(leftChar) == 0) {
                        vowels.remove(leftChar);
                    }
                } else {
                    consonantCount--;
                }
                left++;
            }

            // Count valid substrings
            while (left < right && vowels.size() == 5 && consonantCount == k) {
                result += (nextConsonant[right] - right);
                char leftChar = word.charAt(left);
                if (isVowel(leftChar)) {
                    vowels.put(leftChar, vowels.get(leftChar) - 1);
                    if (vowels.get(leftChar) == 0) {
                        vowels.remove(leftChar);
                    }
                } else {
                    consonantCount--;
                }
                left++;
            }
        }

        return result;
    }
}