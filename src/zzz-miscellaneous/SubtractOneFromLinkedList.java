class SubtractOneFromLinkedList {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static void printList(Node node) {
        while (node != null) {
            System.out.print(node.data);
            node = node.next;
        }
        System.out.println();
    }

    private static boolean subtract(Node head) {
        if (head == null) {
            return false;
        }

        if (subtract(head.next)) {
            return true;
        }

        if (head.data == 0) {
            head.data = 9;
            return false;
        } else {
            head.data -= 1;
            return true;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(0);
        head.next.next = new Node(0);
        head.next.next.next = new Node(0);

        System.out.print("List is ");
        printList(head);

        subtract(head);
        while (head != null && head.data == 0) {
            head = head.next;
        }

        System.out.print("Resultant list is ");
        printList(head);
    }
}