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
        a--;
        b--;
        ListNode curr = list1;
        while (a > 0) {
            curr = curr.next;
            a--;
            b--;
        }
        ListNode temp = curr;
        curr = curr.next;
        temp.next = list2;
        while (temp.next != null) {
            temp = temp.next;
        }
        while (b > 0) {
            curr = curr.next;
            b--;
        }
        temp.next = curr.next;

        return list1;
    }
}