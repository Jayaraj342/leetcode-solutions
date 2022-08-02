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
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head;
        int halfSize = 0;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            halfSize++;
        }

        ListNode prev = slow;
        slow = slow.next;
        ListNode next;
        while (slow != null) {
            next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        ListNode start = head;
        ListNode end = prev;
        while (halfSize > 0) {
            if (start.val != end.val) {
                return false;
            }
            start = start.next;
            end = end.next;
            halfSize--;
        }

        return true;
    }
}