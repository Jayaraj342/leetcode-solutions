class LFUCache {
    final Map<Integer, Node> keyNodeMap;
    int size;
    final int maxCapacity;
    final Map<Integer, Dll> frequencyDllMap;
    int minFrequency;

    public LFUCache(int capacity) {
        this.keyNodeMap = new HashMap<>();
        this.size = 0;
        this.maxCapacity = capacity;
        this.frequencyDllMap = new HashMap<>();
        this.minFrequency = 0;
    }

    public int get(int key) {
        Node node = keyNodeMap.get(key);
        if (node != null) {
            increaseFrequency(node);
            return node.value;
        }
        return -1;
    }

    private void increaseFrequency(Node node) {
        int oldFrequency = node.frequency;

        if (frequencyDllMap.containsKey(oldFrequency)) {
            Dll dllForOldNode = frequencyDllMap.get(oldFrequency);
            dllForOldNode.remove(node);
            if (minFrequency == oldFrequency && dllForOldNode.length == 0) {
                minFrequency++;
            }
        }
        int newFrequency = oldFrequency + 1;
        Dll dllForNewNode = frequencyDllMap.getOrDefault(newFrequency, new Dll());
        node.frequency = newFrequency;
        dllForNewNode.add(node);

        frequencyDllMap.put(newFrequency, dllForNewNode);
    }

    public void put(int key, int value) {
        if(maxCapacity <= 0) return;
        if (keyNodeMap.containsKey(key)) {
            Node oldNode = keyNodeMap.get(key);
            oldNode.value = value;
            increaseFrequency(oldNode);
        } else {
            Node newNode = new Node(key, value);
            if (keyNodeMap.size() == maxCapacity) {
                Dll dllWithMinFrequencyNodes = frequencyDllMap.get(minFrequency);
                if (dllWithMinFrequencyNodes != null) {
                    Node removedNode = dllWithMinFrequencyNodes.remove(dllWithMinFrequencyNodes.right.prev);
                    keyNodeMap.remove(removedNode.key);
                }
            }
            increaseFrequency(newNode);
            keyNodeMap.put(key, newNode);
            minFrequency = 1;
        }
    }
}

class Node {
    int key;
    int value;
    int frequency;
    Node prev;
    Node next;

    Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.frequency = 0;
    }
}

class Dll {
    final Node left;
    final Node right;
    int length;

    Dll() {
        left = new Node(-1, -1);
        right = new Node(-1, -1);
        left.next = right;
        right.prev = left;

        length = 0;
    }

    void add(Node node) {
        Node temp = left.next;

        left.next = node;
        node.prev = left;

        node.next = temp;
        temp.prev = node;

        length++;
    }

    Node remove(Node node) {
        Node tempLeft = node.prev;
        Node tempRight = node.next;

        tempLeft.next = tempRight;
        tempRight.prev = tempLeft;

        length--;
        return node;
    }
}