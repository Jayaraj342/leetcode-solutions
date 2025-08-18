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
        ListNode left = new ListNode(0);// stores values < x
        ListNode right = new ListNode(0);// stores values >= x

        ListNode leftTail = left;
        ListNode rightTail = right;

        while (head != null){
            if(head.val < x){
                leftTail.next = head;
                leftTail = leftTail.next;
            } else {
                rightTail.next = head;
                rightTail = rightTail.next;
            }
            head = head.next;
        }

        leftTail.next = right.next;
        rightTail.next = null;

        return left.next;
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