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
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode prevA = list1;

        // Move `prevA` to the (a-1)th node
        for (int i = 0; i < a - 1; i++) {
            prevA = prevA.next;
        }

        ListNode afterB = prevA.next; // currently at a'th node

        // Move `afterB` to the (b+1)th node => (b - a will reach b'th node)
        for (int i = 0; i < b - a + 1; i++) {
            afterB = afterB.next;
        }

        // Connect `prevA` to `list2`
        prevA.next = list2;

        // Find the tail of `list2`
        ListNode tail = list2;
        while (tail != null && tail.next != null) {
            tail = tail.next;
        }

        // Connect `tail` to `afterB`
        if (tail != null) {
            tail.next = afterB;
        }

        return list1;
    }
}