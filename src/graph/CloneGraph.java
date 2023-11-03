class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

// recursive
class Solution {
    Map<Node, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }
        if (map.containsKey(node)) {
            return map.get(node);
        }

        Node current = new Node(node.val);

        map.put(node, current);
        for (Node nei : node.neighbors) {
            current.neighbors.add(cloneGraph(nei));
        }

        return current;
    }
}

// iterative
class Solution {
    Map<Node, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        map.put(node, new Node(node.val));

        while (!queue.isEmpty()) {
            Node curr = queue.remove();
            for (Node nei : curr.neighbors) {
                if (!map.containsKey(nei)) {
                    queue.add(nei);
                    map.put(nei, new Node(nei.val));
                }
                map.get(curr).neighbors.add(map.get(nei));
            }
        }

        return map.get(node);
    }
}