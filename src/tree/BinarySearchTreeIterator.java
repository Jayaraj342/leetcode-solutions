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

class BSTIterator {
    Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        addAllLeftNodes(root);
    }

    public int next() {
        TreeNode temp = stack.pop();
        addAllLeftNodes(temp.right);
        return temp.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    private void addAllLeftNodes(TreeNode node) {
        while (node != null) {
            stack.add(node);
            node = node.left;
        }
    }
}