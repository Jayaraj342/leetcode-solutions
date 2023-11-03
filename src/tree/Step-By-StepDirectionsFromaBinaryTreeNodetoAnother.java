class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder rootToStart = new StringBuilder();
        StringBuilder rootToDest = new StringBuilder();

        dfs(root, startValue, rootToStart);
        dfs(root, destValue, rootToDest);
        rootToStart.reverse();
        rootToDest.reverse();

        int min = Math.min(rootToStart.length(), rootToDest.length());
        int i = 0;
        while (i < min && rootToStart.charAt(i) == rootToDest.charAt(i)) {
            i++;
        }

        StringBuilder res = new StringBuilder();
        for (int j = i; j < rootToStart.length(); j++) {
            res.append("U");
        }
        for (int j = i; j < rootToDest.length(); j++) {
            res.append(rootToDest.charAt(j));
        }

        return res.toString();
    }

    private boolean dfs(TreeNode root, int value, StringBuilder sb) {
        if (root.val == value) {
            return true;
        }
        if (root.left != null && dfs(root.left, value, sb)) {
            sb.append("L");
        } else if (root.right != null && dfs(root.right, value, sb)) {
            sb.append("R");
        }
        return sb.length() > 0;
    }
}

// Timeout
class Solution {
    Set<TreeNode> visited = new HashSet<>();

    public String getDirections(TreeNode root, int startValue, int destValue) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        parent.put(root, null);

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.add(root);

        TreeNode startNode = null, destNode = null;
        while (!stack.isEmpty()) {
            TreeNode curr = stack.remove();
            if (curr.left != null) {
                parent.put(curr.left, curr);
                stack.add(curr.left);
            }
            if (curr.right != null) {
                parent.put(curr.right, curr);
                stack.add(curr.right);
            }
            if (curr.val == startValue) {
                startNode = curr;
            }
            if (curr.val == destValue) {
                destNode = curr;
            }
        }

        return dfs(startNode, destNode, parent);
    }

    Map<TreeNode, String> map = new HashMap<>();
    private String dfs(TreeNode currentNode, TreeNode destNode, Map<TreeNode, TreeNode> parent) {
        if (currentNode == null || visited.contains(currentNode)) {
            return null;
        }
        if (currentNode == destNode) {
            return "";
        }
        if(map.containsKey(currentNode)) {
            return map.get(currentNode);
        }

        visited.add(currentNode);

        String top = dfs(parent.get(currentNode), destNode, parent);
        String left = dfs(currentNode.left, destNode, parent);
        String right = dfs(currentNode.right, destNode, parent);

        visited.remove(currentNode);

        String result = null;
        if (top != null) {
            result = "U" + top;
        }
        if (left != null) {
            result = "L" + left;
        }
        if (right != null) {
            result = "R" + right;
        }
        map.put(currentNode, result);

        return result;
    }
}