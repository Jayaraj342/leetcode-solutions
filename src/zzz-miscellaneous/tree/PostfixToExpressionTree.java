import java.util.Stack;

class Node {
    char data;
    Node left, right;

    Node(char data) {
        this.data = data;
        this.left = this.right = null;
    }

    Node(char data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}

class PostfixToExpressionTree {
    public static boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/' || c == '^');
    }

    public static void postorder(Node root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data);
    }

    public static void inorder(Node root) {
        if (root == null) {
            return;
        }

        if (isOperator(root.data)) {
            System.out.print("(");
        }

        inorder(root.left);
        System.out.print(root.data);
        inorder(root.right);

        if (isOperator(root.data)) {
            System.out.print(")");
        }
    }

    public static Node construct(String postfix) {
        if (postfix == null || postfix.length() == 0) {
            return null;
        }

        Stack<Node> s = new Stack<>();

        for (char c : postfix.toCharArray()) {
            if (isOperator(c)) {
                Node right = s.pop();
                Node left = s.pop();

                Node node = new Node(c, left, right);

                s.add(node);
            }
            else {
                s.add(new Node(c));
            }
        }

        return s.peek();
    }

    public static void main(String[] args) {
        String postfix = "ab+cde+**";
        Node root = construct(postfix);

        System.out.print("Postfix Expression: ");
        postorder(root);

        System.out.print("\nInfix Expression: ");
        inorder(root);
    }
}

//https://practice.geeksforgeeks.org/problems/evaluation-of-postfix-expression1735