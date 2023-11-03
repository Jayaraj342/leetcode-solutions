class Node {
    int val;
    Node left, right;

    Node(int d) {
        val = d;
        left = right = null;
    }
}

class ChildStatus {
    boolean isBST;
    int size;

    ChildStatus(boolean isBST, int size) {
        this.isBST = isBST;
        this.size = size;
    }
}

class SizeOfMaxBSTInBinaryTree {
    private static int maxSize = 0;
    Node root;

    private static ChildStatus largestBST(Node node, Integer min, Integer max) {
        if (node == null) {
            return new ChildStatus(true, 0);
        }

        if (min != null && node.val < min || max != null && node.val > max) {
            return new ChildStatus(false, 0);
        }

        ChildStatus left = largestBST(node.left, min, node.val);
        ChildStatus right = largestBST(node.right, node.val, max);

        boolean isBST = left.isBST && right.isBST;
        int size = 1 + left.size + right.size;
        maxSize = Math.max(size, maxSize);
        return new ChildStatus(isBST, isBST ? size : 0);
    }

    static class Trunk {
        Trunk prev;
        String str;

        Trunk(Trunk prev, String str) {
            this.prev = prev;
            this.str = str;
        }
    }

    public void printTree(Node root, Trunk prev, boolean isLeft) {
        if (root == null) {
            return;
        }

        String prev_str = "    ";
        Trunk trunk = new Trunk(prev, prev_str);

        printTree(root.right, trunk, true);

        if (prev == null) {
            trunk.str = "———";
        } else if (isLeft) {
            trunk.str = ".———";
            prev_str = "   |";
        } else {
            trunk.str = "`———";
            prev.str = prev_str;
        }

        showTrunks(trunk);
        System.out.println(" " + root.val);

        if (prev != null) {
            prev.str = prev_str;
        }
        trunk.str = "   |";

        printTree(root.left, trunk, false);
    }

    public static void showTrunks(Trunk p) {
        if (p == null) {
            return;
        }

        showTrunks(p.prev);
        System.out.print(p.str);
    }

    public static void main(String[] args) {

        SizeOfMaxBSTInBinaryTree tree = new SizeOfMaxBSTInBinaryTree();
        tree.root = new Node(50);
        tree.root.left = new Node(10);
        tree.root.right = new Node(60);
        tree.root.left.left = new Node(5);
        tree.root.left.right = new Node(20);
        tree.root.right.left = new Node(55);
        tree.root.right.left.left = new Node(45);
        tree.root.right.right = new Node(70);
        tree.root.right.right.left = new Node(65);
        tree.root.right.right.right = new Node(80);

        //tree.printTree(tree.root, null, false);
        largestBST(tree.root.right, null, null);

        System.out.println("Size of largest BST is " + maxSize);
    }
}
