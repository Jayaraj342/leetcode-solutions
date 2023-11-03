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

// preHead -> ptr1 -> ptr2 -> null
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode preHead = new ListNode(0, head);
        ListNode ptr2 = preHead, ptr1 = preHead;

        int n = 0;
        while (ptr2.next != null) {
            ptr2 = ptr2.next;
            n++;
        }

        k = k % n;
        for (int i = 0; i < n - k; i++) {
            ptr1 = ptr1.next;
        }

        ptr2.next = preHead.next;
        preHead.next = ptr1.next;
        ptr1.next = null;

        return preHead.next;
    }
}