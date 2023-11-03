import java.util.*;
import java.util.function.Consumer;

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

    static StringBuilder infixToPostFix(String expression) {

        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : expression.toCharArray()) {
            if (precedence(c) > 0) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
                    result.append(stack.pop());
                }
                stack.push(c);
            } else if (c == ')') {
                char x = stack.pop();
                while (x != '(') {
                    result.append(x);
                    x = stack.pop();
                }
            } else if (c == '(') {
                stack.push(c);
            } else {
                result.append(c);
            }
        }

        for (int i = 0; i <= stack.size(); i++) {
            result.append(stack.pop());
        }
        return result;
    }

    public static void main(String[] args) {
        Consumer<String> consumer = exp -> {
            System.out.println("Infix expression: " + exp);
            System.out.println("Prefix expression: " + infixToPostFix(exp) + "\n");
        };

        consumer.accept("A*B+C");
        consumer.accept("A+B*C");
        consumer.accept("A*(B+C)");
    }
}