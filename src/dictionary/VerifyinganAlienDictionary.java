class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] precedence = new int[26];
        int k = 1;
        for (char c : order.toCharArray()) {
            precedence[c - 'a'] = k++;
        }

        for (int i = 0; i < words.length - 1; i++) {
            String first = words[i];
            String second = words[i + 1];

            int min = Math.min(first.length(), second.length());
            for (int j = 0; j < min; j++) {
                if (first.charAt(j) != second.charAt(j)) {
                    if (precedence[first.charAt(j) - 'a'] > precedence[second.charAt(j) - 'a']) {
                        return false;
                    } else {
                        break;
                    }
                }

                if (j == min - 1 && first.length() > second.length()) {
                    return false;
                }
            }
        }

        return true;
    }
}