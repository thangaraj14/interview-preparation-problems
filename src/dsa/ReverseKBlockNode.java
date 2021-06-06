package dsa;

public class ReverseKBlockNode {

    static Node head;
    static int k = 3;

    public static void main(String[] args) {
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);
        head.next.next.next.next.next.next.next = new Node(8);
        head.next.next.next.next.next.next.next.next = new Node(9);
        head.next.next.next.next.next.next.next.next.next = new Node(10);

        reverseBlocks(head, null);
        print(head);
    }

    private static void reverseBlocks(Node node, Node link) {

        Node current = node;
        Node next;
        Node prev = null;
        int count = 0;
        while (current != null && count < k) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }
        if (link != null) {
            link.next = prev;
        } else {
            head = prev;
        }
        if (current != null) {
            reverseBlocks(current, node);
        }

    }

    private static void print(Node current) {
        Node node = current;
        while (node != null) {
            System.out.println(node.data);
            node = node.next;
        }
    }

}
