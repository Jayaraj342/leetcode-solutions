class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        Map<Character, Integer> charCountMap = new HashMap<>();

        int left = 0;
        int res = 0;

        for (int right = 0; right < n; right++) {
            char currChar = s.charAt(right);

            charCountMap.put(currChar, charCountMap.getOrDefault(currChar, 0) + 1);

            // Maintain the window with all chars present
            while (charCountMap.size() == 3) {
                res += n - right; // Substrings starting from 'left' to 'right' till end are valid

                char leftChar = s.charAt(left);
                charCountMap.put(leftChar, charCountMap.get(leftChar) - 1);
                if (charCountMap.get(leftChar) == 0) {
                    charCountMap.remove(leftChar);
                }

                left++;
            }
        }

        return res;
    }
}