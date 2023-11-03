class Solution {
    public int goodNodes(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE);
    }

    private int dfs(TreeNode root, int maxInPath) {
        if (root == null) {
            return 0;
        }
        int newMaxInPath = Math.max(maxInPath, root.val);
        return (root.val >= maxInPath ? 1 : 0) + dfs(root.left, newMaxInPath) + dfs(root.right, newMaxInPath);
    }
}