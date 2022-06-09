class Solution {
    public Node inorderSuccessor(Node root, Node x) {
        if(x.right != null) {
            Node low = x.right;
            while(low.left != null) {
                low = low.left;
            }
            return low;
        }
        Node temp = root;
        Node res = null;
        while(temp != x) {
            if(x.data > temp.data) {
                temp = temp.right;
            } else {
                res = temp;
                temp = temp.left;
            }
        }

        return res;
    }
}

class Solution {
    public Node inorderSuccessor(Node root, Node x) {
        Stack<Node> stack = new Stack<>();
        Node temp = root;
        boolean xReached = false;
        while (!stack.isEmpty() || temp != null) {
            if (temp == null) {
                Node curr = stack.pop();
                if (xReached) {
                    return curr;
                }
                if (curr == x) {
                    xReached = true;
                }
                temp = curr.right;
            } else {
                stack.add(temp);
                temp = temp.left;
            }
        }

        return null;
    }
}