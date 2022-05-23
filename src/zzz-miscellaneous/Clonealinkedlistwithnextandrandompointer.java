import java.util.*;

class Node {
    int data;
    Node next, arb;

    Node(int d) {
        data = d;
        next = arb = null;

    }
}

class Clone {
    //Function to clone a linked list with next and random pointer.
    Node copyList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        return dfs(head, map);
    }

    private Node dfs(Node node, Map<Node, Node> map) {
        if (node == null) {
            return node;
        }
        if (map.containsKey(node)) {
            return map.get(node);
        }
        Node copy = new Node(node.data);
        map.put(node, copy);

        copy.next = dfs(node.next, map);
        copy.arb = dfs(node.arb, map);

        return copy;
    }
}

// https://practice.geeksforgeeks.org/problems/clone-a-linked-list-with-next-and-random-pointer/1