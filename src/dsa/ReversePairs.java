package dsa;

/**
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 */
public class ReversePairs {

    static Node head;

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);

        Node reversePairs = reversePairsIteration(head);
        printList(reversePairs);
       /* reverseKNodes(head, null);
        printReversedPairs(ReversePairs.head);*/
    }

    public Node reversePairsRec(Node head) {

        if (head == null || head.next == null) {
            return head;
        }

        Node next = head.next;
        Node temp = head.next.next;

        next.next = head;
        head = next;

        head.next.next = reversePairsRec(temp);
        return head;
    }

    private static void printList(Node node) {
        if (node == null) {
            return;
        }
        while (node != null) {
            System.out.println(node.data);
            node = node.next;
        }
    }

    public static Node reversePairsIteration(Node head) {
        if (head == null) {
            return null;
        }

        Node dummy = new Node(-1);
        dummy.next = head;
        Node current = dummy;

        // {dummy->1->2->3->4->null}
        while (current.next != null && current.next.next != null) {
            // current points to dummy in the beginning.
            // first -> 1
            Node first = current.next;
            //second -> 2
            Node second = current.next.next;

            Node third = second.next;

            // dummy->2
            current.next = second;
            //2->1
            second.next = first;
            //1->3
            first.next = third;
            // curr=1
            current = first;
            // now { dummy->2->1->3->4 }
        }
        return dummy.next;
    }
}
