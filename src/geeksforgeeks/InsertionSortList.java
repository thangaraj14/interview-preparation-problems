package geeksforgeeks;

/**
 * https://leetcode.com/problems/insertion-sort-list/
 */
public class InsertionSortList {

    public static ListNode insertionSortList(ListNode head) {
        ListNode curr = head, next;
        ListNode dummy = new ListNode(0);

        while (curr != null) {
            next = curr.next;

            ListNode p = dummy;
            while (p.next != null && p.next.val < curr.val)
                p = p.next;

            // insert curr between p and p.next
            curr.next = p.next;
            p.next = curr;
            curr = next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(6);
        head.next = new ListNode(5);
        head.next.next = new ListNode(10);
        head.next.next.next = new ListNode(3);

        insertionSortList(head);
    }
}

