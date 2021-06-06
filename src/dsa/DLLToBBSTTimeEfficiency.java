package dsa;

public class DLLToBBSTTimeEfficiency {

    static Node head;

    private Node findMiddleNode(Node temp) {
        Node fastPointer, slowPointer;
        fastPointer = slowPointer = temp;

        while (null != fastPointer && null != fastPointer.next) {
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;
        }
        return slowPointer;
    }

    private Node formBBST(Node head) {
        if (null == head) {
            return null;
        }
        if (null == head.next) {
            head.prev = null;
            return head;
        }

        Node root = findMiddleNode(head);
        Node temp = head;

        while (root != temp.next) {
            temp = temp.next;
        }
        temp.next = null;

        root.prev = formBBST(head);
        root.next = formBBST(root.next);

        return root;
    }

    private static void addNode(int i) {

        Node node = new Node(i);
        if (null == head) {
            head = node;
        } else {
            Node temp = head;
            while (null != temp.next) {
                temp = temp.next;
            }
            temp.next = node;
            node.prev = temp;
        }
    }

    public static void main(String[] args) {
        addNode(1);
        addNode(2);
        addNode(3);
        addNode(4);
        addNode(5);
        addNode(6);
        addNode(7);

        DLLToBBSTTimeEfficiency bst = new DLLToBBSTTimeEfficiency();
        bst.preOrder(bst.formBBST(head));

    }

    private void preOrder(Node temp) {
        if (null == temp) {
            return;
        }

        System.out.print(temp.getData() + " ");
        preOrder(temp.prev);
        preOrder(temp.next);

    }
}
