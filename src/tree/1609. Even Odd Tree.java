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
    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 1;
        while (!queue.isEmpty()) {
            int n = queue.size(), lastVal = -1;
            for (int i = 0; i < n; i++) {
                TreeNode curr = queue.remove();
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
                if (level % 2 != 0) { // odd
                    if (curr.val % 2 == 0 || lastVal != -1 && curr.val <= lastVal) {
                        return false;
                    }
                } else {
                    if (curr.val % 2 != 0 || lastVal != -1 && curr.val >= lastVal) {
                        return false;
                    }
                }
                lastVal = curr.val;
            }
            level++;
        }

        return true;
    }
}