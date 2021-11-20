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
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, Comparator.comparingInt(listNode -> listNode.val));

        ListNode dummyHead = new ListNode(0);
        ListNode lastNode = dummyHead;

        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }

        while (!queue.isEmpty()) {
            lastNode.next = queue.poll();
            lastNode = lastNode.next;

            if (lastNode.next != null) {
                queue.add(lastNode.next);
            }
        }
        return dummyHead.next;
    }
}