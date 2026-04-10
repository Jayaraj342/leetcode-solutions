class Solution {
    int maxPath;

    public int maxPathSum(TreeNode root) {
        maxPath = Integer.MIN_VALUE;
        dfs(root);

        return maxPath;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = Math.max(dfs(node.left), 0);
        int right = Math.max(dfs(node.right), 0);
        maxPath = Math.max(maxPath, node.val + left + right);

        return node.val + Math.max(left, right);
    }
}