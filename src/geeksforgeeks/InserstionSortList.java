package geeksforgeeks;

public class InserstionSortList {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;

        // take 1->2->3->4
        while (head != null) {
            ListNode temp = head.next; // at first run temp=2, second run temp=3

        /* Before insert, the prev is at the last node of the sorted list.
           Only the last node's value is larger than the current inserting node
           should we move the temp back to the head*/
            if (prev.val >= head.val) prev = dummy;

            // during second run prev= 0->1->null
            while (prev.next != null && prev.next.val < head.val) {
                prev = prev.next;
            } // after this loop, at first run prev=0 (0->null), second run prev=1

            head.next = prev.next; // we set 1->null // second run 2->null
            prev.next = head; // 0->1 // 0->1->2

            head = temp; // head= 2->3->4 // head= 3->4
        }
        return dummy.next;
    }
}