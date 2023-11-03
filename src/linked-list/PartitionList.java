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
    public ListNode partition(ListNode head, int x) {
        ListNode preHead = new ListNode(-2000, head);
        ListNode slow = preHead;
        ListNode fast = head;

        while (fast != null && fast.val < x) {
            slow = fast;
            fast = fast.next;
        }

        while (fast != null && fast.next != null) {
            if (fast.next.val < x) {
                ListNode temp = fast.next;
                fast.next = fast.next.next;
                temp.next = slow.next;

                slow.next = temp;
                slow = slow.next;
            } else {
                fast = fast.next;
            }
        }

        return preHead.next;
    }
}