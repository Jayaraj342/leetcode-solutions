import java.util.*;

class Node {
    public Node(int val) {
        this.val = val;
        this.neighbors = new ArrayList<>();
    }
    int val;
    List<Node> neighbors;

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof Node)) {
            return false;
        }
        Node nodeObj = (Node) obj;
        return this.val == nodeObj.val && this.neighbors.equals(nodeObj.neighbors);
    }

    @Override
    public int hashCode() {
        return 100;
    }
}

class Solution {
    public static void main(String[] args) {
        Map<Node, Integer> map = new HashMap<>();
        Node first = new Node(1);
        map.put(first, 1);

        Node second = new Node(2);
        System.out.println(map.containsKey(second));
    }
}