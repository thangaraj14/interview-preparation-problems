package linkedLists;

public class ReversePairsNode {
    public ListNode reversePairs(ListNode head) {
        if (head == null) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode current = dummy;


        // dummy->1->2->3->4->null
        //explanation for one loop rest are same.
        while (current.next != null && current.next.next != null) {
            // current points to dummy in the beginning.
            // first -> 1
            ListNode first = current.next;
            //second -> 2
            ListNode second = current.next.next;
            // temp-> 3
            ListNode third = second.next;

            // dummy->2
            current.next = second;

            //1->3
            first.next = third;
            //2->1
            second.next = first;
            // curr=1
            current = first;
            // now  dummy->2->1->3->4
        }

        return dummy.next;
    }
    // 1->2->3->4->5->null
    public Node reversePairsRec(Node head) {

        if (head == null || head.next == null) {
            return head;
        }

        Node next = head.next; // next= 2
        Node temp = head.next.next; // temp= 3

        next.next = head; // 2->1
        head = next;// head=2->1

        head.next.next = reversePairsRec(temp); // the rest will be taken care by recursion
        return head;
    }

}
