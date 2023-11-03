class Solution {
    ArrayList<Integer> boundary(Node node) {
        ArrayList<Integer> result = new ArrayList<>();
        result.add(node.data);

        addLeftNodes(node.left, result);
        addLeaves(node.left, result);
        addLeaves(node.right, result);
        addRightNodes(node.right, result);

        return result;
    }

    private void addLeftNodes(Node node, ArrayList<Integer> result) {
        if (node == null) {
            return;
        }

        if (node.left != null) {
            result.add(node.data);
            addLeftNodes(node.left, result);
        } else if(node.right != null) {
            result.add(node.data);
            addLeftNodes(node.right, result);
        }
    }

    private void addLeaves(Node node, ArrayList<Integer> result) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            result.add(node.data);
        }
        addLeaves(node.left, result);
        addLeaves(node.right, result);
    }

    private void addRightNodes(Node node, ArrayList<Integer> result) {
        if (node == null) {
            return;
        }

        if (node.right != null) {
            addRightNodes(node.right, result);
            result.add(node.data);
        } else if (node.left != null) {
            addRightNodes(node.left, result);
            result.add(node.data);
        }
    }
}