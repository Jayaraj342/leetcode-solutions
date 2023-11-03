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
    public void flatten(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        dfs(root, queue);

        TreeNode prev = null, curr;
        while (!queue.isEmpty()) {
            curr = queue.remove();
            if (prev != null) {
                prev.right = curr;
                prev.left = null;
            }
            prev = curr;
        }
    }

    private void dfs(TreeNode node, Queue<TreeNode> queue) {
        if (node == null) {
            return;
        }
        queue.add(node);
        dfs(node.left, queue);
        dfs(node.right, queue);
    }
}

class Solution {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;

        flatten(left);
        flatten(right);

        root.right = left;

        TreeNode temp = root;
        while (temp.right != null) {
            temp = temp.right;
        }
        temp.right = right;
    }
}