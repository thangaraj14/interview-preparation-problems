package geeksforgeeks;

/**
 * https://www.techiedelight.com/flatten-linked-list/
 * <p>
 * https://www.geeksforgeeks.org/flattening-a-linked-list/
 */
class FlattenLinkedList {
    
    Node head;

    class Node {
        int data;
        Node right, down;

        Node(int data) {
            this.data = data;
            right = null;
            down = null;
        }

        @Override
        public String toString() {
            return "" + this.data;
        }
    }

    Node mergeIterative(Node a, Node b) {
        Node dummy = new Node(0);
        Node result = dummy;

        while (a != null && b != null) {
            if (a.data < b.data) {
                result.down = a;
                a = a.down;
            } else {
                result.down = b;
                b = b.down;
            }
            result = result.down;
        }
        while (a != null) {
            result.down = a;
            result = result.down;
            a = a.down;
        }

        while (b != null) {
            result.down = b;
            result = result.down;
            b = b.down;
        }
        return dummy.down;
    }

    Node flatten(Node root) {
        if (root == null || root.right == null) {
            return root;
        }

        root.right = flatten(root.right);

        return mergeIterative(root, root.right);

    }

    public static void main(String args[]) {
        flattenList();
    }

    protected static void flattenList() {
        FlattenLinkedList list = new FlattenLinkedList();
  
        /* Let us create the following linked list 
            5 -> 10 -> 19 -> 28 
            |    |     |     | 
            V    V     V     V 
            7    20    22    35 
            |          |     | 
            V          V     V 
            8          50    40 
            |                | 
            V                V 
            30               45 
        */

        list.head = list.push(list.head, 30);
        list.head = list.push(list.head, 8);
        list.head = list.push(list.head, 7);
        list.head = list.push(list.head, 5);

        list.head.right = list.push(list.head.right, 20);
        list.head.right = list.push(list.head.right, 10);

        list.head.right.right = list.push(list.head.right.right, 50);
        list.head.right.right = list.push(list.head.right.right, 22);
        list.head.right.right = list.push(list.head.right.right, 19);

        list.head.right.right.right = list.push(list.head.right.right.right, 45);
        list.head.right.right.right = list.push(list.head.right.right.right, 40);
        list.head.right.right.right = list.push(list.head.right.right.right, 35);

        // flatten the list 
        list.head = list.flatten(list.head);

        list.printList();
    }

    Node push(Node head_ref, int data) {
        Node new_node = new Node(data);
        new_node.down = head_ref;
        head_ref = new_node;

        return head_ref;
    }

    void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.down;
        }
        System.out.println();
    }
}