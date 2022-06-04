class Solution {
    public int evalRPN(String[] tokens) {
        Set<String> operators = Set.of("+", "-", "*", "/");

        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            int temp;
            if (operators.contains(token)) {
                int right = stack.pop();
                int left = stack.pop();
                switch (token) {
                    case "+":
                        temp = left + right;
                        break;
                    case "-":
                        temp = left - right;
                        break;
                    case "*":
                        temp = left * right;
                        break;
                    default:
                        temp = left / right;
                        break;
                }
                stack.add(temp);
            } else {
                stack.add(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}