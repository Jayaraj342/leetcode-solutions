class Solution {
    public int calculate(String s) {
        s = s.replace(" ", "");

        char prevOper = '+';
        Stack<Integer> stack = new Stack<>();

        int num = 0;
        int i = 0;
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                i--;
                if (prevOper == '+') {
                    stack.push(num);
                } else if (prevOper == '-') {
                    stack.push(-num);
                } else if (prevOper == '*') {
                    stack.push(stack.pop() * num);
                } else if (prevOper == '/') {
                    stack.push(stack.pop() / num);
                }
                num = 0;
            } else {
                prevOper = s.charAt(i);
            }
            i++;
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }

        return res;
    }
}