class Solution {
    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();

        if (k == num.length()) {
            return "0";
        }

        for (char c : num.toCharArray()) {
            while (!stack.isEmpty() && stack.peek() > c && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }

        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            char c = stack.pop();
            if (k == 0) {
                res.insert(0, c);
            } else {
                k--;
            }
        }

        while (res.length() > 1 && res.charAt(0) == '0') {
            res.deleteCharAt(0);
        }

        return res.toString();
    }
}