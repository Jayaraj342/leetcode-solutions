import static java.lang.Math.max;

class Node {
    int val, height;
    Node left, right;

    Node(int d) {
        val = d;
        height = 1;
    }
}

class AVLTree {
    Node root;

    int height(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        return y;
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
        return x;
    }

    // Get balance factor of a node
    int getBalanceFactor(Node node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    // Insert a node
    Node insertNode(Node node, int val) {

        // Find the position and insert the node
        if (node == null)
            return new Node(val);
        if (val < node.val)
            node.left = insertNode(node.left, val);
        else if (val > node.val)
            node.right = insertNode(node.right, val);
        else
            return node;

        // Update the balance factor of each node
        // And, balance the tree
        node.height = 1 + max(height(node.left), height(node.right));
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) {
            if (val < node.left.val) {
                return rightRotate(node);
            } else if (val > node.left.val) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }
        if (balanceFactor < -1) {
            if (val > node.right.val) {
                return leftRotate(node);
            } else if (val < node.right.val) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }

        return node;
    }

    Node nodeWithMinimumValue(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    // Delete a node
    Node deleteNode(Node root, int item) {

        // Find the node to be deleted and remove it
        if (root == null)
            return root;
        if (item < root.val)
            root.left = deleteNode(root.left, item);
        else if (item > root.val)
            root.right = deleteNode(root.right, item);
        else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;
                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                Node temp = nodeWithMinimumValue(root.right);
                root.val = temp.val;
                root.right = deleteNode(root.right, temp.val);
            }
        }
        if (root == null)
            return root;

        // Update the balance factor of each node and balance the tree
        root.height = max(height(root.left), height(root.right)) + 1;
        int balanceFactor = getBalanceFactor(root);
        if (balanceFactor > 1) {
            if (getBalanceFactor(root.left) >= 0) {
                return rightRotate(root);
            } else {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
        }
        if (balanceFactor < -1) {
            if (getBalanceFactor(root.right) <= 0) {
                return leftRotate(root);
            } else {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
        }
        return root;
    }

    // Print the tree
    private void printTree(Node currPtr, String indent) {
        if (currPtr != null) {
            printTree(currPtr.right, indent + "     ");
            System.out.println();
            System.out.print(indent);
            System.out.println(currPtr.val);
            printTree(currPtr.left, indent + "     ");
        }
    }

    // Driver code
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.root = tree.insertNode(tree.root, 13);
        tree.root = tree.insertNode(tree.root, 33);
        tree.root = tree.insertNode(tree.root, 53);

        tree.printTree(tree.root, "");

        tree.root = tree.deleteNode(tree.root, 13);

        System.out.println("After Deletion: ");
        tree.printTree(tree.root, "");
    }
}