class LFUCache {

    // Doubly Linked List Node
    private class Node {
        int key, value, freq;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }

    // Doubly Linked List for each frequency
    private class DoublyLinkedList {
        Node head, tail;
        int size;

        DoublyLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        void addNode(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            size++;
        }

        void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        Node removeLastNode() {
            if (size == 0) return null;
            Node node = tail.prev;
            removeNode(node);
            return node;
        }
    }

    private final int capacity;
    private int currSize, minFreq;
    private final Map<Integer, Node> nodeMap;         // key → Node
    private final Map<Integer, DoublyLinkedList> freqMap;  // freq → DLL

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.currSize = 0;
        this.minFreq = 0;
        this.nodeMap = new HashMap<>();
        this.freqMap = new HashMap<>();
    }

    public int get(int key) {
        Node node = nodeMap.get(key);
        if (node == null) return -1;

        updateFreq(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (nodeMap.containsKey(key)) {
            Node node = nodeMap.get(key);
            node.value = value;
            updateFreq(node);
        } else {
            if (currSize == capacity) {
                // Remove LFU node
                DoublyLinkedList minFreqList = freqMap.get(minFreq);
                Node toRemove = minFreqList.removeLastNode();
                nodeMap.remove(toRemove.key);
                currSize--;
            }

            // Insert new node
            Node newNode = new Node(key, value);
            nodeMap.put(key, newNode);
            freqMap.computeIfAbsent(1, k -> new DoublyLinkedList()).addNode(newNode);
            minFreq = 1;
            currSize++;
        }
    }

    private void updateFreq(Node node) {
        int oldFreq = node.freq;
        DoublyLinkedList oldList = freqMap.get(oldFreq);
        oldList.removeNode(node);

        if (oldFreq == minFreq && oldList.size == 0) {
            minFreq++;
        }

        node.freq++;
        freqMap.computeIfAbsent(node.freq, k -> new DoublyLinkedList()).addNode(node);
    }
}