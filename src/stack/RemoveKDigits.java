class Solution {
    public String removeKdigits(String num, int k) {
        int n = num.length();
        if (k >= n) return "0";

        Stack<Character> stack = new Stack<>();

        // Build the smallest number by removing larger digits before smaller ones
        for (char c : num.toCharArray()) {
            while (!stack.isEmpty() && k > 0 && stack.peek() > c) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }

        // If k still > 0, remove from the end (largest remaining digits)
        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }

        // Build the result
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }

        // Remove leading zeros
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return sb.toString();
    }
}