// n, 1
class Solution {
    public int minOperations(String s) {
        // Task is to find first occuring char that is not 'a'
        char min = '{'; // '{' is just after 'z' in ASCII
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != 'a' && c < min) {
                min = c;
                if (min == 'b') break; // can't get smaller than 'b'
            }
        }

        return min == '{' ? 0 : ('z' - min) + 1;
    }
}