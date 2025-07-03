class Solution {
    Map<Integer, List<TreeNode>> memo = new HashMap<>();

    public List<TreeNode> allPossibleFBT(int n) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        if (n == 1) {
            return List.of(new TreeNode(0));
        }

        List<TreeNode> list = new ArrayList<>();
        for (int leftNodes = 1; leftNodes < n - 1; leftNodes++) {
            int rightNodes = n - 1 - leftNodes;

            for (TreeNode leftNode : allPossibleFBT(leftNodes)) {
                for (TreeNode rightNode : allPossibleFBT(rightNodes)) {
                    TreeNode root = new TreeNode(0);
                    root.left = leftNode;
                    root.right = rightNode;

                    list.add(root);
                }
            }
        }

        return list;
    }
}

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