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
    public ListNode rotateRight(ListNode head, int n) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0, head);
        ListNode fast = dummyHead, slow = dummyHead;

        int len = 0;
        while (fast.next != null) {
            fast = fast.next;
            len++;
        }

        for (int i = len - n % len; i > 0; i--) {
            slow = slow.next;
        }

        fast.next = dummyHead.next;
        dummyHead.next = slow.next;
        slow.next = null;

        return dummyHead.next;
    }
}