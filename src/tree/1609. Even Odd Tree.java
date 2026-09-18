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

        boolean evenLevel = true; // root is level 0
        while (!queue.isEmpty()) {
            int n = queue.size();
            int prev = evenLevel ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                TreeNode curr = queue.remove();

                // Even level: values must be odd and strictly increasing
                if (evenLevel) {
                    if (curr.val % 2 == 0 || curr.val <= prev) {
                        return false;
                    }
                }
                // Odd level: values must be even and strictly decreasing
                else {
                    if (curr.val % 2 != 0 || curr.val >= prev) {
                        return false;
                    }
                }

                prev = curr.val;

                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
            }

            evenLevel = !evenLevel;
        }

        return true;
    }
}