import java.util.*;

class solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            String s = sc.next();
            Node root = happyTrees(s, n);

            System.out.println(happyTreesCount(root));
        }
    }

    private static int happyTreesCount(Node root) {
        if (root.children.isEmpty()) {
            return 0;
        }

        int count = 0;
        for (Node child : root.children) {
            count += child.children.size();
        }
        if(count == 0) {
            return 0;
        }

        int anyCount = 0;
        int childCount = 0;
        boolean isChildCountSame = true;
        for (Node child : root.children) {
            if (anyCount == 0 && !child.children.isEmpty()) {
                anyCount = child.children.size();
            }
            if (anyCount != 0 && child.children.size() != anyCount) {
                isChildCountSame = false;
            }
            childCount += happyTreesCount(child);
        }

        if (isChildCountSame && anyCount != 0) {
            childCount++;
        }

        return childCount;
    }

    private static Node happyTrees(String s, int n) {
        if (s.equals("") || s.equals("[]")) {
            return new Node(1);
        }

        Node root = new Node(1);
        int curr = 0, start = 0;
        while (curr < n) {
            if (isBalanced(start, curr, s)) {
                Node child = happyTrees(s.substring(start + 1, curr), curr - start - 1);
                if (child != null) {
                    root.children.add(child);
                }
                start = curr + 1;
            }
            curr++;
        }

        return root;
    }

    private static boolean isBalanced(int start, int curr, String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = start; i <= curr; i++) {
            if (s.charAt(i) == '[') {
                stack.add('[');
            } else {
                stack.pop();
            }
            if (stack.isEmpty()) {
                return true;
            }
        }

        return stack.isEmpty();
    }
}

class Node {
    int val;
    List<Node> children = new ArrayList<>();

    Node(int val) {
        this.val = val;
    }
}