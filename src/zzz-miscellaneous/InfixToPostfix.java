import java.util.Stack;

class InfixToPostfix {
    static int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    static StringBuilder infixToPreFix(String expression) {

        StringBuilder result = new StringBuilder();
        StringBuilder input = new StringBuilder(expression);
        Stack<Character> stack = new Stack<>();

        char[] charsExp = new String(input).toCharArray();

        for (char c : charsExp) {
            if (precedence(c) > 0) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
                    result.insert(0, stack.pop());
                }
                stack.push(c);
            } else if (c == ')') {
                char x = stack.pop();
                while (x != '(') {
                    result.insert(0, x);
                    x = stack.pop();
                }
            } else if (c == '(') {
                stack.push(c);
            } else {
                result.insert(0, c);
            }
        }

        for (int i = 0; i <= stack.size(); i++) {
            result.insert(0, stack.pop());
        }
        return result.reverse();
    }

    public static void main(String[] args) {
        String exp = "a+(b-c)*d";
        System.out.println("Infix Expression: " + exp);
        System.out.println("Prefix Expression: " + infixToPreFix(exp));
    }
}
