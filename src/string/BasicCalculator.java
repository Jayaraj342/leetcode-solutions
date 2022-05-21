class Solution {
    // 21 + 3 - ( 1 +10)
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int currentNum = 0;
        int resultSoFar = 0;
        int sign = 1;

        int i = 0;
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    currentNum = currentNum * 10 + (s.charAt(i) - '0');
                    i++;
                }
                i--;
                resultSoFar += sign * currentNum;
                currentNum = 0;
                sign = 1;
            } else if (s.charAt(i) == '+') {
                sign = 1;
            } else if (s.charAt(i) == '-') {
                sign = -1;
            } else if (s.charAt(i) == '(') {
                stack.push(resultSoFar);
                stack.push(sign);

                resultSoFar = 0;
                sign = 1;
            } else if (s.charAt(i) == ')') {
                resultSoFar *= stack.pop();
                resultSoFar += stack.pop();
            }
            i++;
        }

        return resultSoFar;
    }
}