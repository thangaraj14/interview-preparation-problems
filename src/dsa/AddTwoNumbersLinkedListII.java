package dsa;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: l1 = [7,2,4,3], l2 = [5,6,4]
 * Output: [7,8,0,7]
 * <p>
 * Example 2:
 * <p>
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [8,0,7]
 * <p>
 * Example 3:
 * <p>
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 * <p>
 * <p>
 * <p>
 * Follow up: Could you solve it without reversing the input lists?
 */
public class AddTwoNumbersLinkedListII {

    public static Node addTwoNumbers(Node l1, Node l2) {

        Deque<Long> s1 = new ArrayDeque<>();
        Deque<Long> s2 = new ArrayDeque<>();

        while (l1 != null) {
            s1.push(l1.data);
            l1 = l1.next;
        }

        while (l2 != null) {
            s2.push(l2.data);
            l2 = l2.next;
        }

        int sum = 0;
        Node list = new Node(0);
        while (!s1.isEmpty() || !s2.isEmpty()) {
            if (!s1.isEmpty()) {
                sum += s1.pop();
            }
            if (!s2.isEmpty()) {
                sum += s2.pop();
            }
            list.data = sum % 10;
            Node head = new Node(sum / 10);
            head.next = list;
            list = head;
            sum /= 10;
        }
        return list.data == 0 ? list.next : list;
    }

    public static void main(String[] args) {
        Node l1 = new Node(2);
        l1.next = new Node(4);
        l1.next.next = new Node(3);

        Node l2 = new Node(5);
        l2.next = new Node(6);
        l2.next.next = new Node(4);

        addTwoNumbers(l1, l2);
    }
}