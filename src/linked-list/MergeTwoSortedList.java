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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }
        list2.next = mergeTwoLists(list1, list2.next);
        return list2;
    }
}

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(0);
        ListNode last = preHead;

        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                last.next = l2;
                l2 = l2.next;
            } else {
                last.next = l1;
                l1 = l1.next;
            }
            last = last.next;
        }

        if (l1 == null) {
            last.next = l2;
        } else {
            last.next = l1;
        }

        return preHead.next;
    }
}
