class Solution {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return dfs(root).node;
    }

    private Result dfs(TreeNode node) {
        if (node == null) {
            return new Result(null, 0);
        }

        Result left = dfs(node.left);
        Result right = dfs(node.right);

        if (left.h == right.h) {
            return new Result(node, left.h + 1);
        }

        return left.h > right.h ? new Result(left.node, left.h + 1) : new Result(right.node, right.h + 1);
    }

    private static class Result {
        TreeNode node;
        int h;

        Result(TreeNode node, int h) {
            this.node = node;
            this.h = h;
        }
    }
}

class Solution {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        // Level order traversal - to get all leaf nodes
        // create parent map in the process

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        Queue<TreeNode> prevLevel = queue;
        Map<Integer, TreeNode> parent = new HashMap<>();
        while (!queue.isEmpty()) {
            prevLevel = new LinkedList<>(queue);
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode last = queue.remove();
                if (last.left != null) {
                    queue.add(last.left);
                    parent.put(last.left.val, last);
                }
                if (last.right != null) {
                    queue.add(last.right);
                    parent.put(last.right.val, last);
                }
            }
        }

        // If there is one leaf, itself is the LCA
        if (prevLevel.size() == 1) {
            return prevLevel.remove();
        }

        // keep only first and last node of leaves
        TreeNode first = prevLevel.remove();
        while (prevLevel.size() > 1) {
            prevLevel.remove();
        }
        TreeNode last = prevLevel.remove();

        TreeNode firstParent = parent.get(first.val), lastParent = parent.get(last.val);
        while (firstParent.val != lastParent.val) {
            firstParent = parent.get(firstParent.val);
            lastParent = parent.get(lastParent.val);
        }

        return firstParent;
    }
}

// need not store all nodes of last level - only first and last are enough
class Solution {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        // Level order traversal - to get all leaf nodes
        // create parent map in the process

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        Queue<TreeNode> prevLevel = queue;
        Map<Integer, TreeNode> parent = new HashMap<>();
        while (!queue.isEmpty()) {
            prevLevel = new LinkedList<>(queue);
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode last = queue.remove();
                if (last.left != null) {
                    queue.add(last.left);
                    parent.put(last.left.val, last);
                }
                if (last.right != null) {
                    queue.add(last.right);
                    parent.put(last.right.val, last);
                }
            }
        }

        // If there is one leaf, itself is the LCA
        if (prevLevel.size() == 1) {
            return prevLevel.remove();
        }

        queue = prevLevel;
        while (queue.size() != 1) {
            int size = queue.size(), lastParent = -1;
            for (int i = 0; i < size; i++) {
                TreeNode last = queue.remove();
                TreeNode parentNode = parent.get(last.val);
                if (!queue.isEmpty() && parentNode.val == lastParent) {
                    continue;
                }
                lastParent = parentNode.val;
                queue.add(parentNode);
            }
        }

        return queue.remove();
    }

    public static void main(String[] args) {
        // [3,5,1,6,2,0,8,null,null,7,4]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);

        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        new Solution().lcaDeepestLeaves(root);
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