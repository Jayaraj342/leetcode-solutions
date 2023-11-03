class Node {
    int val;
    Node left, right;

    Node(int item) {
        val = item;
        left = right = null;
    }
}

class IsSumTree {
    public Node root;

    static int isSumTree(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return node.val;
        }

        int left = isSumTree(node.left);
        int right = isSumTree(node.right);

        if (left + right != node.val) {
            return 0;
        }
        return 2 * node.val;
    }

    public static void main(String[] args) {
        IsSumTree tree = new IsSumTree();
        tree.root = new Node(26);
        tree.root.left = new Node(10);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(6);
        tree.root.right.right = new Node(3);

        if (isSumTree(tree.root) == 2 * tree.root.val) {
            System.out.println("The given tree is a SumTree");
        } else {
            System.out.println("The given tree is not a SumTree");
        }
    }
}
