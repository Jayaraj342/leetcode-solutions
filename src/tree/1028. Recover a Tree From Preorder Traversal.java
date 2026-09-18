class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public TreeNode recoverFromPreorder(String traversal) {
        Stack<TreeNode> stack = new Stack<>();
        int i = 0;

        while (i < traversal.length()) {
            // Find depth
            int depth = 0;
            while (i < traversal.length() && traversal.charAt(i) == '-') {
                depth++;
                i++;
            }

            // Find value
            int val = 0;
            while (i < traversal.length() && traversal.charAt(i) != '-') {
                val = val * 10 + (traversal.charAt(i) - '0');
                i++;
            }

            TreeNode node = new TreeNode(val);

            // Move to parent at depth - 1
            while (stack.size() > depth) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                TreeNode parent = stack.peek();

                if (parent.left == null) {
                    parent.left = node;
                } else {
                    parent.right = node;
                }
            }

            stack.push(node);
        }

        return stack.get(0); // root
    }
}

class Solution {
    private int idx;

    public TreeNode recoverFromPreorder(String traversal) {
        idx = 0;
        return buildTree(traversal, 0);
    }

    private TreeNode buildTree(String traversal, int depth) {
        int dashCount = 0;
        while (idx + dashCount < traversal.length() && traversal.charAt(idx + dashCount) == '-') {
            dashCount++;
        }

        if (dashCount != depth) return null; // If dashes don't match expected depth, return

        idx += dashCount;
        int num = 0;
        while (idx < traversal.length() && Character.isDigit(traversal.charAt(idx))) {
            num = num * 10 + (traversal.charAt(idx) - '0');
            idx++;
        }

        TreeNode node = new TreeNode(num);
        node.left = buildTree(traversal, depth + 1);
        node.right = buildTree(traversal, depth + 1);

        return node;
    }
}
