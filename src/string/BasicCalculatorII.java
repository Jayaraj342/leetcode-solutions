class Solution {
    public int calculate(String s) {
        s = s.replace(" ", "");

        Stack<Integer> stack = new Stack<>();
        char prevChar = '+';

        int i = 0, n = s.length();
        while (i < n) {
            char curr = s.charAt(i);
            if (Character.isDigit(curr)) {
                int num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }

                switch (prevChar) {
                    case '+': stack.push(num); break;
                    case '-': stack.push(-num); break;
                    case '*': stack.push(stack.pop() * num); break;
                    case '/': stack.push(stack.pop() / num); break;
                }
            } else {
                prevChar = curr;
                i++;
            }
        }

        int res = 0;
        for (int num : stack) {
            res += num;
        }

        return res;
    }
}

// Basic calculator |||
class Solution {
    public int calculate(String s) {
        // remove spaces
        s = s.replaceAll("\\s+", "");

        Deque<Integer> values = new ArrayDeque<>();
        values.push(0);
        Deque<Character> ops = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                // parse full number
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                values.push(num);
                i--; // adjust because for‐loop will i++

            } else if (c == '(') {
                ops.push(c);

            } else if (c == ')') {
                // evaluate until matching '('
                while (ops.peek() != '(') applyOneOp(values, ops);
                ops.pop(); // drop '('
            } else { // operator +, -, * or /
                // pop any ops of higher-or-equal precedence
                while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(c)) {
                    applyOneOp(values, ops);
                }
                ops.push(c);
            }
        }

        // finish any remaining ops
        while (!ops.isEmpty()) {
            applyOneOp(values, ops);
        }

        return values.pop();
    }

    private void applyOneOp(Deque<Integer> values, Deque<Character> ops) {
        int b = values.pop(), a = values.pop();
        char op = ops.pop();
        int res;
        switch (op) {
            case '+':
                res = a + b;
                break;
            case '-':
                res = a - b;
                break;
            case '*':
                res = a * b;
                break;
            default:
                res = a / b;
                break;
        }
        values.push(res);
    }

    private int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0; // for '('
    }

    public static void main(String[] args) {
        System.out.println(new Solution().calculate("-1+2"));
    }
}
