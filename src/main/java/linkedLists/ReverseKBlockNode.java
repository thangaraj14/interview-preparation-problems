package linkedLists;

/**
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 *
 * tricky
 */
public class ReverseKBlockNode {

    static ListNode head;
    static int k = 3;

    public static void main(String[] args) {
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next.next.next.next = new ListNode(9);
        head.next.next.next.next.next.next.next.next.next = new ListNode(10);

        reverseKNodes(head, 2);
        print(head);
    }


    private static void print(ListNode current) {
        ListNode node = current;
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public static ListNode reverseKNodes(ListNode head, int k) {
        if (head == null) return null;
        ListNode root = head;
        int count = 0;
        //1. test weather we have more then k node left, if less then k node left we just return head
        while (count < k) { // && root!=null add this condition to reverse remaining elements
            //base case: head listnode contains less than k nodes,
            // in this case, return the original listnode(aka head)
            if (root == null) return head;
            root = root.next;
            count++;
        }
        // 2.reverse k node at current level
        ListNode prev = reverseKNodes(root, k); //prev node point to the answer of sub-problem
        while (count > 0) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
            count--;

        }

        return prev;
    }

    /**
     * Reverse a link list between begin and end exclusively
     * an example:
     * a linked list:
     * 0->1->2->3->4->5->6
     * |           |
     * begin       end
     * after call begin = reverse(begin, end)
     *
     * 0->3->2->1->4->5->6
     *          |  |
     *      begin end
     * @return the reversed list's 'begin' node, which is the precedence of node end
     */

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode begin;
        if (head == null || head.next == null || k == 1)
            return head;

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        begin = dummyHead;
        int i = 0;

        while (head != null) {
            i++;
            ListNode next = head.next;
            if (i % k == 0) {
                begin = reverse(begin, next);
            }
                head = next;

        }
        return dummyHead.next;

    }

    public ListNode reverse(ListNode begin, ListNode end) {
        ListNode curr = begin.next;
        ListNode prev = begin;
        ListNode first = curr;
        while (curr != end) {
           ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        begin.next = prev;
        first.next = curr;
        return first;
    }

}
