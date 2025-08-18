class Solution {
    public int romanToInt(String s) {
        int n = s.length();
        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            int a = getValue(s.charAt(i));
            int b = getValue(s.charAt(i + 1));
            if (a < b) {
                res -= a;
            } else {
                res += a;
            }
        }
        res += getValue(s.charAt(n - 1));

        return res;
    }

    public int getValue(char c) {
        if (c == 'I') return 1;
        else if (c == 'V') return 5;
        else if (c == 'X') return 10;
        else if (c == 'L') return 50;
        else if (c == 'C') return 100;
        else if (c == 'D') return 500;
        else return 1000;
    }
}

// 12. Integer to Roman
// https://leetcode.com/problems/integer-to-roman/
class Solution {
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                sb.append(symbols[i]);
                num -= values[i];
            }
        }

        return sb.toString();
    }
}