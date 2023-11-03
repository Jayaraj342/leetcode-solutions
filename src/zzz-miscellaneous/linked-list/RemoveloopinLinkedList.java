// https://practice.geeksforgeeks.org/problems/remove-loop-in-linked-list/1

class Node {
    int data;
    Node next;
}

// space O(n)
class Solution {
    public static void removeLoop(Node head){
        Set<Node> set = new HashSet<>();

        Node temp = head;
        while(temp != null) {
            if(set.contains(temp.next)) {
                temp.next = null;
            }
            set.add(temp);
            temp = temp.next;
        }
    }
}

// space O(1)
class Solution {
    public static void removeLoop(Node head) {
        findLoop(head, head, head);
    }

    private static void findLoop(Node slow, Node fast, Node head) {
        if (slow == null || fast == null || fast.next == null) {
            return;
        }
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) {
            removeLoop(slow, head);
            return;
        }

        findLoop(slow, fast, head);
    }

    private static void removeLoop(Node nodeInLoop, Node head) {
        int loopSize = 1;
        Node temp = nodeInLoop;
        while (temp.next != nodeInLoop) {
            temp = temp.next;
            loopSize++;
        }

        temp = head;
        while (loopSize > 0) {
            temp = temp.next;
            loopSize--;
        }

        while (temp != head) {
            temp = temp.next;
            head = head.next;
        }

        // Cannot use temp.next != head.next in above loop because, when head forms loop it won't work
        while(temp.next != head) {
            temp = temp.next;
        }

        temp.next = null;
    }
}