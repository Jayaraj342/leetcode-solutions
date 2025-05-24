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
