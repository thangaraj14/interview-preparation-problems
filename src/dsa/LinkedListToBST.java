package dsa;

class LinkedListToBST {

    static LNode head;

    class LNode {
        int data;
        LNode next, prev;

        LNode(int d) {
            data = d;
            next = prev = null;
        }
    }

    class TNode {
        int data;
        TNode left, right;

        TNode(int d) {
            data = d;
            left = right = null;
        }
    }

    TNode sortedListToBST() {
        int n = countNodes(head);
        return sortedListToBSTRecur(n);
    }

    TNode sortedListToBSTRecur(int n) {
	    if (n <= 0) {
		    return null;
	    }

        TNode left = sortedListToBSTRecur(n / 2);
        TNode root = new TNode(head.data);
        root.left = left;
        head = head.next;
        root.right = sortedListToBSTRecur(n - n / 2 - 1);

        return root;
    }

    int countNodes(LNode head) {
        int count = 0;
        LNode temp = head;
        while (temp != null) {
            temp = temp.next;
            count++;
        }
        return count;
    }

    /*
     * Function to insert a node at the beginging of the Doubly Linked List
     */
    void push(int new_data) {
        /* allocate node */
        LNode new_node = new LNode(new_data);

        /*
         * since we are adding at the begining, prev is always NULL
         */
        new_node.prev = null;

        /* link the old list off the new node */
        new_node.next = head;

        /* change prev of head node to new node */
	    if (head != null) {
		    head.prev = new_node;
	    }

        /* move the head to point to the new node */
        head = new_node;
    }

    void printList(LNode node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    void preOrder(TNode node) {
	    if (node == null) {
		    return;
	    }
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void main(String[] args) {
        LinkedListToBST llist = new LinkedListToBST();

        llist.push(7);
        llist.push(6);
        llist.push(5);
        llist.push(4);
        llist.push(3);
        llist.push(2);
        llist.push(1);

        System.out.println("Given Linked List ");
        llist.printList(head);

        TNode root = llist.sortedListToBST();
        System.out.println("");
        System.out.println("Pre-Order Traversal of constructed BST ");
        llist.preOrder(root);
    }
}