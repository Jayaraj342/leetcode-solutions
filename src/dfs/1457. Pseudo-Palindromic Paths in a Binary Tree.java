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
    public int pseudoPalindromicPaths(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int mask) {
        if (root == null) {
            return 0;
        }

        // Toggle the bit for this digit
        mask ^= (1 << root.val);

        // Leaf: at most one digit can have odd frequency
        if (root.left == null && root.right == null) {
            return (mask & (mask - 1)) == 0 ? 1 : 0;
        }

        return dfs(root.left, mask) + dfs(root.right, mask);
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