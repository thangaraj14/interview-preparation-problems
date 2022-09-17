package practiceproblems;

/**
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 */
class RandomLinkedList {

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node dummy = new Node(-1);
        dummy.next = head;

        while (head != null) {
            Node newNode = new Node(head.val);
            newNode.next = head.next;
            head.next = newNode;
            head = newNode.next;
        }

        head = dummy.next;

        while (head != null && head.next != null) {

            head.next.random = head.random != null ? head.random.next : null;
            head = head.next.next;
        }

        Node oldHead = dummy.next;
        Node newHead = dummy.next.next;
        Node newTemp = newHead;

        while (oldHead != null) {
            oldHead.next = oldHead.next.next;
            newHead.next = newHead.next == null ? null : newHead.next.next;

            oldHead = oldHead.next;
            newHead = newHead.next;
        }

        return newTemp;
    }

    static class Node {
        public int val;
        public Node next;
        public Node random;

        public Node(int _val) {
            val = _val;
            next = null;
            random = null;
        }

        public String toString() {
            return "" + this.val;
        }
    }
}

