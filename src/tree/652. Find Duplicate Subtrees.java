// n, n
class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> idMap = new HashMap<>();
        Map<Integer, Integer> countMap = new HashMap<>();
        List<TreeNode> result = new ArrayList<>();

        serialize(root, idMap, countMap, result);

        return result;
    }

    private int serialize(
            TreeNode node,
            Map<String, Integer> idMap,
            Map<Integer, Integer> countMap,
            List<TreeNode> result
    ) {

        if (node == null) {
            return 0;
        }

        int leftId = serialize(node.left, idMap, countMap, result);
        int rightId = serialize(node.right, idMap, countMap, result);

        String key = node.val + "," + leftId + "," + rightId;
        int id = idMap.computeIfAbsent(key, k -> idMap.size() + 1);

        int count = countMap.getOrDefault(id, 0);
        if (count == 1) {
            result.add(node);
        }

        countMap.put(id, count + 1);

        return id;
    }
}

// n * k, n * k => k - avg length of string (worst case n^2 tc and sc)
class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> subtreeCount = new HashMap<>();
        List<TreeNode> duplicates = new ArrayList<>();
        serialize(root, subtreeCount, duplicates);

        return duplicates;
    }

    private String serialize(TreeNode node, Map<String, Integer> countMap, List<TreeNode> result) {
        if (node == null) return "#";

        String serial = node.val + "," + serialize(node.left, countMap, result) + "," + serialize(node.right, countMap, result);

        int count = countMap.getOrDefault(serial, 0);
        if (count == 1) {
            result.add(node); // add only once
        }
        countMap.put(serial, count + 1);
        return serial;
    }
}