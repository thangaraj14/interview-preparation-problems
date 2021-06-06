package dsa;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
 */
public class PopulateNextRightPointer {

    public Node connect(Node root) {

        Node levelStart = root;
        while (levelStart != null) {
            Node cur = levelStart;
            while (cur != null) {
                if (cur.left != null) {
                    cur.left.next = cur.right;
                    if (cur.next != null) {
                        cur.right.next = cur.next.left; // we don't need to check for the right child
                    }
                }
                cur = cur.next;
            }
            levelStart = levelStart.left;
        }
        return root;
    }

    //based on level order traversal
    public void connectII(Node root) {

        Node head = null; //head of the next level
        Node prev = null; //the leading node on the next level
        Node cur = root;  //current node of current level

        while (cur != null) {

            while (cur != null) { //iterate on the current level
                //left child
                if (cur.left != null) {
                    if (prev != null) {
                        prev.next = cur.left;
                    } else {
                        head = cur.left;
                    }
                    prev = cur.left;
                }
                //right child
                if (cur.right != null) {
                    if (prev != null) {
                        prev.next = cur.right;
                    } else {
                        head = cur.right;
                    }
                    prev = cur.right;
                }
                //move to next node
                cur = cur.next;
            }

            //move to next level
            cur = head;
            head = null;
            prev = null;
        }

    }
}
