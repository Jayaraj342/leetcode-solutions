class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    TreeNode prev;

    public void flatten(TreeNode root) {
        prev = new TreeNode(-1);

        preOrder(root);
    }

    public void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        TreeNode left = node.left;
        TreeNode right = node.right;

        prev.right = node;
        prev = prev.right;
        prev.left = null;

        preOrder(left);
        preOrder(right);
    }
}