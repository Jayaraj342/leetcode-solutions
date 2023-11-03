class Solution {
    public boolean isValid(String s) {
        List<Character> openList = Arrays.asList('(', '{', '[');
        List<Character> closeList = Arrays.asList(')', '}', ']');

        Stack<Character> stack = new Stack<>();
        for (Character bracket : s.toCharArray()) {
            if (openList.contains(bracket)) {
                stack.push(bracket);
            } else if (!stack.isEmpty() && openList.get(closeList.indexOf(bracket)) == stack.peek()) {
                stack.pop();
            } else {
                return false;
            }
        }

        return stack.isEmpty();
    }
}