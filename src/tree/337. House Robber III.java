class Solution {
    public int rob(TreeNode root) {
        Result res = dfs(root);
        return Math.max(res.with, res.without);
    }

    private Result dfs(TreeNode root) {
        if (root == null) {
            return new Result();
        }

        Result left = dfs(root.left), right = dfs(root.right);
        Result curr = new Result();
        curr.with = root.val + left.without + right.without;
        curr.without = Math.max(left.with, left.without) + Math.max(right.with, right.without);

        return curr;
    }

    static class Result {
        int with = 0;
        int without = 0;
    }
}