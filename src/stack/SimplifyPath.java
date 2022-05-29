class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        Set<String> skip = Set.of("..", ".", "");
        for (String dir : path.split("/")) {
            if (dir.equals("..") && !stack.isEmpty()) {
                stack.pop();
            } else if (!skip.contains(dir)) {
                stack.add(dir);
            }
        }

        String res = "";
        for (String dir : stack) {
            res += "/" + dir;
        }

        return stack.isEmpty() ? "/" : res;
    }
}