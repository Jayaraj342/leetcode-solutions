class Solution {
    public int calculate(String s) {
        int resultSoFar = 0, sign = 1;
        int currNum = 0;

        Stack<Integer> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                currNum = currNum * 10 + (c - '0');
            } else {
                resultSoFar += sign * currNum;
                currNum = 0;
                if (c == '(') {
                    stack.add(resultSoFar);
                    resultSoFar = 0;
                    stack.add(sign);
                    sign = 1;
                } else if (c == ')') {
                    resultSoFar *= stack.pop();// sign
                    resultSoFar += stack.pop();// old sum
                } else if (c == '+') {
                    sign = 1;
                } else if (c == '-') {
                    sign = -1;
                }
            }
        }
        resultSoFar += sign * currNum;// Add remaining number

        return resultSoFar;
    }
}

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