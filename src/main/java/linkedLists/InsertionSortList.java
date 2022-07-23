package linkedLists;

/**
 * tricky linked list sort
 *
 * Sort a linked list using insertion sort.
 * Algorithm of Insertion Sort:
 * Insertion sort iterates,
 * consuming one input element each repetition, and growing a sorted output list.
 * At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
 * It repeats until no input elements remain.
 */
public class InsertionSortList {
    public static ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode curr = head;

        while (curr != null) {
            // At each iteration, we insert an element into the resulting list.
            ListNode prev = dummy;

            // find the position to insert the current node
            while (prev.next != null && prev.next.val < curr.val) {
                prev = prev.next;
            }

            ListNode next = curr.next;

            // this means given me what ever you have in next so that i(curr) can keep as next items
            curr.next = prev.next;
            // i(curr) got your next items, let me(curr) attach as your next item.
            // this is the insertion part of the algo
            prev.next = curr;

            // moving on to the next iteration
            curr = next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(7);
        node.next = new ListNode(3);
        node.next.next = new ListNode(2);
        node.next.next.next = new ListNode(8);
        node.next.next.next.next = new ListNode(9);
        node.next.next.next.next.next = new ListNode(1);

        insertionSortList(node);
    }
}