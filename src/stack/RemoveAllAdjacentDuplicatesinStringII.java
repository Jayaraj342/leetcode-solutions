class Solution {
    public String removeDuplicates(String s, int k) {
        Stack<CharCount> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (stack.isEmpty()) {
                stack.add(new CharCount(c, 1));
            } else {
                CharCount last = stack.peek();
                if (last.c == c) {
                    int prevDuplicates = last.count;
                    if (prevDuplicates != k - 1) {
                        last.count++;
                    } else {
                        stack.pop();
                    }
                } else {
                    stack.add(new CharCount(c, 1));
                }
            }
        }
        StringBuilder res = new StringBuilder();
        for (CharCount charCount : stack) {
            for (int i = 0; i < charCount.count; i++) {
                res.append(charCount.c);
            }
        }

        return res.toString();
    }

    static class CharCount {
        char c;
        int count;

        CharCount(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }
}