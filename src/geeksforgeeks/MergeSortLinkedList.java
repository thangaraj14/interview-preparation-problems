package geeksforgeeks;

public class MergeSortLinkedList {

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // step 1. cut the list to two halves
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        // find the mid node
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        // step 2. sort each half
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);
        // step 3. merge l1 and l2
        return merge(l1, l2);
    }
    static ListNode merge(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(0);
        ListNode temp = node;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        if (l1 != null) {
            temp.next = l1;
        }
        if (l2 != null) {
            temp.next = l2;
        }
        return node.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(5);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(4);
        ListNode listNode = sortList(head);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

}

class ListNode {

    ListNode next;
    int val;

    public ListNode(int val) {
        this.val = val;
    }
}