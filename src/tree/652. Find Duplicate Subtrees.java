// n * k, n * k => k - avg length of string
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