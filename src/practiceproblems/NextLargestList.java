package practiceproblems;

import java.util.Stack;

/**
 * https://leetcode.com/problems/next-greater-node-in-linked-list/
 */
class NextLargestList {

    public int[] nextLargerNodes(ListNode head) {

        if (head == null) {
            return new int[0];
        }
        int size = getSize(head);
        int[] result = new int[size];
        head = reverse(head);
        Stack<Integer> stack = new Stack<>();
        stack.push(head.val);
        head = head.next;
        int i = size - 2;
        result[size - 1] = 0;
        while (head != null) {

            while (!stack.isEmpty() && stack.peek() <= head.val) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? 0 : stack.peek();
            stack.push(head.val);
            head = head.next;
            i--;
        }

        return result;
    }

    public int getSize(ListNode head) {
        int size = 0;
        ListNode root = head;
        while (root != null) {
            root = root.next;
            size++;
        }
        return size;
    }

    public ListNode reverse(ListNode head) {
        ListNode root = head;
        ListNode prev = null;
        while (root != null) {
            ListNode next = root.next;
            root.next = prev;
            prev = root;
            root = next;
        }
        return prev;
    }
}