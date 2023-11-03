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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode preHead = new ListNode(-1, head);
        removeNthFromEndHelper(preHead, n);

        return preHead.next;
    }

    private int removeNthFromEndHelper(ListNode node, int n) {
        if (node.next == null) {
            return 1;
        }

        int count = removeNthFromEndHelper(node.next, n);

        if (count == n) {
            node.next = node.next.next;
        }

        return count + 1;
    }
}