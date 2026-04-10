/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        pushLeft(root1, stack1);
        pushLeft(root2, stack2);

        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            Stack<TreeNode> stack = pick(stack1, stack2);
            TreeNode node = stack.pop();
            res.add(node.val);
            pushLeft(node.right, stack);
        }

        return res;
    }

    private void pushLeft(TreeNode root, Stack<TreeNode> stack) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    private Stack<TreeNode> pick(Stack<TreeNode> s1, Stack<TreeNode> s2) {
        if (s1.isEmpty()) return s2;
        if (s2.isEmpty()) return s1;
        return s1.peek().val < s2.peek().val ? s1 : s2;
    }
}