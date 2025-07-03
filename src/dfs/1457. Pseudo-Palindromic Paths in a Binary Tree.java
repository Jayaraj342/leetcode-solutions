// TC : O(n), SC : O(h)
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
    int odd;
    int[] cnt;

    public int pseudoPalindromicPaths(TreeNode root) {
        odd = 0;
        cnt = new int[10];
        return dfs(root);
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        cnt[root.val] += 1;
        int oddChange = cnt[root.val] % 2 == 1 ? 1 : -1;
        odd += oddChange;

        int res = 0;
        if (root.left == null && root.right == null) {
            res += odd <= 1 ? 1 : 0;
        } else {
            res += dfs(root.left) + dfs(root.right);
        }

        odd -= oddChange;
        cnt[root.val] -= 1;

        return res;
    }
}