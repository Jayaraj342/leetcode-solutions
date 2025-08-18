class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) return head;

        ListNode preHead = new ListNode(-1, head);
        ListNode prevGroup = preHead;

        while (true) {
            // 1. Find the kth node from groupPrev
            ListNode kth = getKthNode(prevGroup, k);
            if (kth == null) break; // Not enough nodes to reverse

            ListNode nextGroup = kth.next;

            // 2. Reverse the group
            ListNode prev = nextGroup;
            ListNode curr = prevGroup.next;

            while (curr != nextGroup) {
                ListNode temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            // 3. Reconnect with the previous part
            ListNode temp = prevGroup.next; // Will become the end of this group
            prevGroup.next = kth;
            prevGroup = temp;
        }

        return preHead.next;
    }

    private ListNode getKthNode(ListNode start, int k) {
        while (start != null && k > 0) {
            start = start.next;
            k--;
        }
        return start;
    }
}

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) {
            return head;
        }

        ListNode curr = head, prev = null, next = null;
        int count = 0;
        while (count < k && curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            count++;
        }
        head.next = reverseKGroup(next, k);

        if(count < k) {
            return reverseKGroup(prev, count);
        }

        return prev;
    }
}