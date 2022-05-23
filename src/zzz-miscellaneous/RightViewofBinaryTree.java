import java.util.*;

class Node {
    int data;
    Node left, right;

    public Node(int data) {
        this.data = data;
        left = right = null;
    }
}


class Solution {
    int minHeight = 0;

    ArrayList<Integer> rightView(Node node) {
        ArrayList<Integer> result = new ArrayList<>();
        dfs(node, 1, result);
        return result;
    }

    private void dfs(Node root, int currentHeight, ArrayList<Integer> result) {
        if (root == null) {
            return;
        }
        if (currentHeight > minHeight) {
            result.add(root.data);
            minHeight = currentHeight;
        }
        dfs(root.right, currentHeight + 1, result);
        dfs(root.left, currentHeight + 1, result);
    }
}

// https://practice.geeksforgeeks.org/problems/right-view-of-binary-tree/1