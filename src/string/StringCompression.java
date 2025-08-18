class Solution {
    public int compress(char[] chars) {
        int write = 0;  // Position to write next character
        int read = 0;   // Position to read current character

        while (read < chars.length) {
            char currentChar = chars[read];
            int count = 0;

            // Count the number of occurrences of the current character
            while (read < chars.length && chars[read] == currentChar) {
                read++;
                count++;
            }

            // Write the character
            chars[write++] = currentChar;

            // Write the count if greater than 1
            if (count > 1) {
                for (char c : Integer.toString(count).toCharArray()) {
                    chars[write++] = c;
                }
            }
        }

        return write;
    }
}

class Solution {
    public int compress(char[] chars) {
        int index = 0;
        int i = 0;
        while (i < chars.length) {
            int j = i;
            while (i < chars.length && chars[i] == chars[j]) {
                i++;
            }
            chars[index++] = chars[j];

            int count = i - j;
            if (i - j > 1) {
                for (char num : Integer.toString(count).toCharArray()) {
                    chars[index++] = num;
                }
            }
        }

        return index;
    }
}