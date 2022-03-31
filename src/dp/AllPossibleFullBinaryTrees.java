class Solution {
    Map<Integer, List<TreeNode>> memo = new HashMap<>();

    public List<TreeNode> allPossibleFBT(int n) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        List<TreeNode> list = new ArrayList<>();

        if (n == 0) {
            return list;
        }
        if (n == 1) {
            list.add(new TreeNode(0));
            return list;
        }

        if (n % 2 == 1) {
            for (int leftNodes = 0; leftNodes < n; leftNodes++) {
                int rightNodes = n - leftNodes - 1;

                for (TreeNode leftNode : allPossibleFBT(leftNodes)) {
                    for (TreeNode rightNode : allPossibleFBT(rightNodes)) {
                        TreeNode root = allPossibleFBT(1).get(0);
                        root.left = leftNode;
                        root.right = rightNode;

                        list.add(root);
                    }
                }
            }
        }

        return list;
    }
}