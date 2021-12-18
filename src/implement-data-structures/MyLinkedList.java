class Node<T> {
    T value;
    Node<T> next;

    Node(T value) {
        this.value = value;
    }
}

class MyLinkedList<T> {
    private Node<T> head;

    public void add(T element) {
        if (head == null) {
            head = new Node<>(element);
        } else {
            Node<T> temp = new Node<>(element);
            temp.next = head;
            head = temp;
        }
    }

    public void removeFirst() {
        Node<T> temp = head;
        head = head.next;
        temp.next = null;
    }

    public void removeLast() {
        if(head == null || head.next == null) {
            head = null;
            return;
        }
        Node<T> temp = head;
        Node<T> prev = null;
        while (temp.next != null) {
            prev = temp;
            temp = temp.next;
        }
        prev.next = null;
    }

    public void print() {
        Node<T> temp = head;
        while (temp != null) {
            System.out.print(temp.value + " -> ");
            temp = temp.next;
        }
        System.out.print("null");
        System.out.println();
    }
}

class Main {
    public static void main(String[] args) {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();

        for (int i = 11; i >= 10; i--) {
            myLinkedList.add(i);
        }
        myLinkedList.print();

        myLinkedList.removeFirst();
        myLinkedList.print();

        myLinkedList.removeLast();
        myLinkedList.print();
    }
}
