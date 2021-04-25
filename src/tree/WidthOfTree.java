package tree;

import java.util.LinkedList;
import java.util.Queue;

public class WidthOfTree {
    Node root;

    public static void main(String[] args) {
        WidthOfTree tree = new WidthOfTree();
        tree.root = new Node(10);
        tree.root.left = new Node(2);
        tree.root.right = new Node(10);
        tree.root.left.left = new Node(20);
        tree.root.left.right = new Node(1);
        tree.root.right.right = new Node(-25);
        tree.root.right.left = new Node(3);
        tree.root.right.right.right = new Node(4);

        System.out.println(tree.findWidth());
    }

    public int findWidth() {
        Node node = root;
	    if (node == null) {
		    return 0;
	    }

        Queue<Node> queue = new LinkedList<Node>();
        Queue<Node> result = new LinkedList<Node>();
        queue.add(node);
        result.add(node);
        int count = 0;
        while (true) {
            int size = queue.size();
	        if (size == 0) {
		        break;
	        }
	        if (count < size) {
		        count = size;
	        }
            while (size > 0) {
                Node remove = queue.remove();
                System.out.print(remove.data + " ");
                if (remove.left != null) {
                    queue.add(remove.left);
                }
                if (remove.right != null) {
                    queue.add(remove.right);
                }
                size--;
            }
            System.out.println();
        }
        return count;
    }

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int val) {
            this.data = val;
        }
    }
}
