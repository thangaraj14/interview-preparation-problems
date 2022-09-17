package linkedLists;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/remove-duplicates-from-an-unsorted-linked-list
 */
public class RemoveDuplicates {

    public ListNode deleteDuplicatesFromSorted(ListNode head) {
        ListNode list = head;

        while(list != null) {
            if (list.next == null) {
                break;
            }
            if (list.val == list.next.val) {
                list.next = list.next.next;
            } else {
                list = list.next;
            }
        }

        return head;
    }

    public ListNode deleteDuplicatesFromSortedII(ListNode head) {
        // sentinel
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        // predecessor = the last node before the sublist of duplicates
        // this will be always one node behind
        ListNode pred = sentinel;

        while (head != null) {
            // if it's a beginning of duplicates sublist
            // skip all duplicates
            if (head.next != null && head.val == head.next.val) {
                // move till the end of duplicates sublist
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                // skip all duplicates
                pred.next = head.next;
                // otherwise, move predecessor
            } else {
                pred = pred.next;
            }

            // move forward
            head = head.next;
        }
        return sentinel.next;
    }

    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        // Key: val  Value: its frequency
        Map<Integer, Integer> map = new HashMap<>();
        ListNode curr = head;
        while (curr != null) {
            map.put(curr.val, map.getOrDefault(curr.val, 0) + 1);
            curr = curr.next;
        }

        ListNode dummy = new ListNode(0);
        curr = dummy;
        while (head != null) {
            if (map.get(head.val) == 1) {
                curr.next = head;
                curr = curr.next;
            }

            head = head.next;
        }

        curr.next = null;
        return dummy.next;
    }
}
