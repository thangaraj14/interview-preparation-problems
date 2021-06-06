package dsa;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/merge-k-sorted-lists/
 */
public class MergeKLists {

    public ListNode mergeKLists(List<ListNode> lists) {
        
        if (lists == null || lists.isEmpty()) {
            return null;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparing(ListNode::getVal));

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (ListNode node : lists)
            if (node != null) {
                queue.add(node);
            }

        while (!queue.isEmpty()) {
            tail.next = queue.poll();
            tail = tail.next;

            if (tail.next != null) {
                queue.add(tail.next);
            }
        }
        return dummy.next;
    }
}