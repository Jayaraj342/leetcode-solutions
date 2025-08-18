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
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy, fast = dummy;

        // Move fast pointer n+1 steps ahead to create a gap
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Move both pointers until fast reaches the end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Remove the target node
        slow.next = slow.next.next;

        return dummy.next;
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