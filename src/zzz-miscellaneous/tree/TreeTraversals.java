//https://practice.geeksforgeeks.org/problems/inorder-traversal/1#

class Solution {
    ArrayList<Integer> inOrder(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node itr = root;
        while(!stack.isEmpty() || itr != null) {
            if(itr != null) {
                stack.add(itr);
                itr = itr.left;
            } else {
                Node temp = stack.pop();
                result.add(temp.data);
                itr = temp.right;
            }
        }
        return result;
    }
}

class BinaryTree {
    static ArrayList<Integer> preorder(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        while(!stack.isEmpty()) {
            Node temp = stack.pop();
            result.add(temp.data);
            if(temp.right != null) {
                stack.add(temp.right);
            }
            if(temp.left != null) {
                stack.add(temp.left);
            }
        }
        return result;
    }
}

class Tree {
    ArrayList<Integer> postOrder(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        while(!stack.isEmpty()) {
            Node temp = stack.pop();
            result.add(0, temp.data);
            if(temp.left != null) {
                stack.add(temp.left);
            }
            if(temp.right != null) {
                stack.add(temp.right);
            }
        }
        return result;
    }
}

// https://leetcode.com/problems/binary-tree-level-order-traversal/
// https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/submissions/