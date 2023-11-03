class Node {
    int key;
    int val;
    Node next;
    Node prev;

    Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

class DoublyLinkedList {
    Node left;
    Node right;

    DoublyLinkedList() {
        left = new Node(0, 0);
        right = new Node(0, 0);
        left.next = right;
        right.prev = left;
    }

    public void remove(Node current) {
        Node prev = current.prev;
        Node next = current.next;
        prev.next = next;
        next.prev = prev;
    }

    public Node removeLeft() {
        Node tempLeft = left.next;
        remove(left.next);
        return tempLeft;
    }

    public void insertRight(Node current) {
        Node tempLeft = right.prev;
        right.prev = current;
        current.next = right;

        tempLeft.next = current;
        current.prev = tempLeft;
    }
}

class LRUCache {
    Map<Integer, Node> map;
    DoublyLinkedList list;
    int capacity;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        list = new DoublyLinkedList();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node current = map.get(key);
        list.remove(current);
        list.insertRight(current);
        return current.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            list.remove(map.get(key));
            map.remove(key);
        }

        if (map.size() == capacity) {
            Node removed = list.removeLeft();
            map.remove(removed.key);
        }
        Node current = new Node(key, value);
        list.insertRight(current);
        map.put(key, current);
    }
}