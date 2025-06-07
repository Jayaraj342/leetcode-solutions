class Node implements Iterable<Node> {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
    }

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public Iterator<Node> iterator() {
        return new NodeIterator(this);
    }

    static class NodeIterator implements Iterator<Node> {

        private Node curr;

        public NodeIterator(Node root) {
            curr = root;
        }

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public Node next() {
            Node temp = curr;
            curr = curr.next;
            return temp;
        }
    }
}