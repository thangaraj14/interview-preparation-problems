package dsa;

public class AllProblems {

    static Node head;
    static int size;

    public static void main(String[] args) {
        addNode(2);
        addNode(5);
        addNode(8);
        addNode(10);
        addNode(12);
        // findNthNode(3);
        // findCyclic();
        // reverseLinkedList();
        // middleOfTheLinkedList();
        // printListInReverse(head);
        // reversePairLinkedListIterative();
        // reverseLinkedListUsingRecursiveMethod(head);
        // cloneRandomLinkedList();
        // printList();
        // reverseLinkedListPractise();
        Node head2 = new Node(1);
        head2.next = new Node(4);
        head2.next.next = new Node(7);
        head2.next.next = new Node(9);
        head2.next.next.next = new Node(19);
        head2.next.next.next.next = new Node(29);
        Node result = mergeList(head, head2);

        while (result != null) {
            System.out.print(result.data + " ");
            result = result.next;
        }
    }

    private static Node mergeList(Node a, Node b) {
        System.out.println(a + "->" + b);
        Node c;
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }

        if (a.data < b.data) {
            c = a;
            c.next = mergeList(a.next, b);
        } else {
            c = b;
            c.next = mergeList(b.next, a);
        }
        return c;
    }

    private static Node reverseLinkedListUsingRecursiveMethod(Node curr) {

        if (null == curr) {
            return null;
        }

        Node prev = reverseLinkedListUsingRecursiveMethod(curr.next);
        if (null != prev) {
            prev.next = curr;
        } else {
            head = curr;
        }
        return curr;

    }

    private static void reversePairLinkedListIterative() {

        Node temp1 = null;
        Node realHead = null;
        while (head != null && head.next != null) {
            if (temp1 != null) {
                temp1.next.next = head.next;
            }

            temp1 = head.next;
            head.next = head.next.next;
            temp1.next = head;
            if (realHead == null) {
                realHead = temp1;
            }
            head = head.next;
        }
        while (realHead != null) {
            System.out.println(realHead.data);
            realHead = realHead.next;
        }
    }

    private static Object printListInReverse(Node temp) {
        if (temp != null) {
            printListInReverse(temp.next);
            System.out.println(temp.getData());
        }
        return null;
    }

    private static void middleOfTheLinkedList() {

        Node fastPointer = head;
        Node slowPointer = head;
        while (fastPointer.next != null && fastPointer.next.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
            if (fastPointer.next == null || fastPointer.next.next == null) {
                System.out.println(slowPointer.getData());
            }
        }
    }

    private static void reverseLinkedListPractise() {
        Node curr = head;
        Node prev = null;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        curr = prev;
        while (curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
    }

    private static void findCyclic() {
        Node slowPointer = head;
        Node fastPointer = head;
        System.out.println("slow pointer : " + slowPointer.getData() + "and Fast pointer :" + fastPointer.getData());
        while (fastPointer.next != null && fastPointer.next.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
            if (slowPointer == fastPointer) {
                System.out.println("Gotcha its cyclic" + slowPointer + "::" + fastPointer);
                return;
            } else {
                System.out.println("slow pointer : " + slowPointer + "and Fast pointer :" + fastPointer);
            }
        }
    }

    private static void findNthNode(int n) {
        Node temp = head;
        Node nthNode = head;
        int index = 1;
        while (index < n) {
            temp = temp.next;
            index++;
        }

        while (temp.next != null) {
            temp = temp.next;
            nthNode = nthNode.next;
        }

        System.out.println("Nth Node from end: " + nthNode.getData());

    }

    private static void printList() {
        Node temp = head;
        int index = 0;
        while (index < size) {
            System.out.println(temp.getData());
            temp = temp.next;
            index++;
        }
    }

    private static void addNode(int data) {
        Node node = new Node(data);
        if (head == null) {
            head = node;
            node.next = null;
        } else {
            Node temp = head;
            int index = 1;
            while (index < size) {
                temp = temp.next;
                index++;
            }
            temp.next = node;
            node.next = null;
        }
        size++;
    }
}
