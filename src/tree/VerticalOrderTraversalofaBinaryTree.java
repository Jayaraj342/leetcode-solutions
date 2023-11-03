// If we use map of map of priorityQueue order will be N
// now n.log(n)
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
    PriorityQueue<Status> minHeap;

    static class Status {
        int r;
        int c;
        int val;

        Status(int r, int c, int val) {
            this.r = r;
            this.c = c;
            this.val = val;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        minHeap = new PriorityQueue<>(
                (a, b) -> a.c != b.c ? a.c - b.c : (a.r != b.r ? a.r - b.r : a.val - b.val)
        );
        dfs(root, 0, 0);

        List<List<Integer>> res = new ArrayList<>();
        int prevCol = Integer.MIN_VALUE;
        while (!minHeap.isEmpty()) {
            Status curr = minHeap.remove();
            if (curr.c != prevCol) {
                res.add(new ArrayList<>());
                prevCol = curr.c;
            }
            res.get(res.size() - 1).add(curr.val);
        }

        return res;
    }

    private void dfs(TreeNode node, int r, int c) {
        if (node == null) {
            return;
        }
        minHeap.add(new Status(r, c, node.val));
        dfs(node.left, r + 1, c - 1);
        dfs(node.right, r + 1, c + 1);
    }
}

class Solution {
    // Map<Column, TreeMap<Row, Values>>
    Map<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map;

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }

        map = new TreeMap<>();
        dfs(root, 0, 0);

        List<List<Integer>> res = new ArrayList<>();
        for (int col : map.keySet()) {
            List<Integer> list = new ArrayList<>();
            TreeMap<Integer, PriorityQueue<Integer>> valByRow = map.get(col);
            for (PriorityQueue<Integer> pq : valByRow.values()) {
                while (!pq.isEmpty()) {
                    list.add(pq.poll());
                }
            }
            res.add(list);
        }

        return res;
    }

    private void dfs(TreeNode root, int col, int row) {
        if (root == null) {
            return;
        }

        map.putIfAbsent(col, new TreeMap<>());
        map.get(col).putIfAbsent(row, new PriorityQueue<>());
        map.get(col).get(row).add(root.val);

        dfs(root.left, col - 1, row + 1);
        dfs(root.right, col + 1, row + 1);
    }
}