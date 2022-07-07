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