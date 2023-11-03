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
    int currPre = 0;
    Map<Integer, Integer> ioIndexByValueMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        ioIndexByValueMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            ioIndexByValueMap.put(inorder[i], i);
        }
        return dfs(preorder, 0, preorder.length - 1);
    }

    private TreeNode dfs(int[] preorder, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        int curr = preorder[currPre++];
        TreeNode root = new TreeNode(curr);

        int ioIndex = ioIndexByValueMap.get(curr);
        root.left = dfs(preorder, lo, ioIndex - 1);
        root.right = dfs(preorder, ioIndex + 1, hi);

        return root;
    }
}