class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    int count = 0;

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode(0, head);
        recursive(start, n);

        return start.next;
    }

    private void recursive(ListNode node, int n) {
        if (node.next != null) {
            recursive(node.next, n);
        }
        count++;

        if (count == n + 1) {
            node.next = node.next.next;
        }
    }
}