package geeksforgeeks;

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
        if (root == null || root.right == null)
            return root;

        root.right = flatten(root.right);

        root = mergeIterative(root, root.right);

        return root;
    }


    public static void main(String args[]) {
        flattenList();
    }

    protected static void flattenList() {
        FlattenLinkedList L = new FlattenLinkedList();
  
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

        L.head = L.push(L.head, 30);
        L.head = L.push(L.head, 8);
        L.head = L.push(L.head, 7);
        L.head = L.push(L.head, 5);

        L.head.right = L.push(L.head.right, 20);
        L.head.right = L.push(L.head.right, 10);

        L.head.right.right = L.push(L.head.right.right, 50);
        L.head.right.right = L.push(L.head.right.right, 22);
        L.head.right.right = L.push(L.head.right.right, 19);

        L.head.right.right.right = L.push(L.head.right.right.right, 45);
        L.head.right.right.right = L.push(L.head.right.right.right, 40);
        L.head.right.right.right = L.push(L.head.right.right.right, 35);

        // flatten the list 
        L.head = L.flatten(L.head);

        L.printList();
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

    Node merge(Node a, Node b) {
        if (a == null) return b;

        if (b == null) return a;

        Node result;

        if (a.data < b.data) {
            result = a;
            result.down = merge(a.down, b);
        } else {
            result = b;
            result.down = merge(a, b.down);
        }

        return result;
    }
}