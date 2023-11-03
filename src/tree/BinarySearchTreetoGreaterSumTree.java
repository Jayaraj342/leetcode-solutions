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
    int curr = 0;
    public TreeNode bstToGst(TreeNode root) {
        if(root == null) {
            return null;
        }

        bstToGst(root.right);
        root.val = root.val + curr;
        curr = root.val;
        bstToGst(root.left);

        return root;
    }
}