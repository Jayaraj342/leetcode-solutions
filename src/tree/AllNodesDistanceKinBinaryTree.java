class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        dfs(root, null, parentMap);
        parentMap.remove(root);

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(target);

        Set<TreeNode> visited = new HashSet<>();
        visited.add(target);
        while (!queue.isEmpty()) {
            if (k == 0) {
                List<Integer> res = new ArrayList<>();
                for (TreeNode node : queue) {
                    res.add(node.val);
                }
                return res;
            }
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.remove();
                if (curr.left != null && !visited.contains(curr.left)) {
                    queue.add(curr.left);
                    visited.add(curr.left);
                }
                if (curr.right != null && !visited.contains(curr.right)) {
                    queue.add(curr.right);
                    visited.add(curr.right);
                }
                if (parentMap.containsKey(curr) && !visited.contains(parentMap.get(curr))) {
                    queue.add(parentMap.get(curr));
                    visited.add(parentMap.get(curr));
                }
            }
            k--;
        }

        return new ArrayList<>();
    }

    private void dfs(TreeNode child, TreeNode parent, Map<TreeNode, TreeNode> parentMap) {
        if (child == null) {
            return;
        }
        parentMap.put(child, parent);

        dfs(child.left, child, parentMap);
        dfs(child.right, child, parentMap);
    }
}