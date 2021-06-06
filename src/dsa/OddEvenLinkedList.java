package dsa;

/**
 * https://leetcode.com/problems/odd-even-linked-list/
 */
public class OddEvenLinkedList {

    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode temp = head;
        ListNode tempHead = head.next;
        if (tempHead == null) {
            return head;
        }
        ListNode tempTraversal = head.next;
        while (temp != null && temp.next != null) {
            if (temp.next.next != null) {
                temp.next = temp.next.next;
                temp = temp.next;
            } else {
                break;
            }
            if (tempTraversal.next != null) {
                tempTraversal.next = tempTraversal.next.next;
                tempTraversal = tempTraversal.next;
            }
        }
        temp.next = tempHead;
        return head;
    }
}
