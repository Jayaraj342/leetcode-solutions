public class Solution {
    public static String smallestNumber(String pattern) {
        int n = pattern.length();
        StringBuilder result = new StringBuilder();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <= n; i++) {
            stack.push(i + 1);  // Push numbers from 1 to n+1

            if (i == n || pattern.charAt(i) == 'I') {  // Resolve decreasing sequence on 'I' or end
                while (!stack.isEmpty()) {
                    result.append(stack.pop());
                }
            }
        }

        return result.toString();
    }
}
