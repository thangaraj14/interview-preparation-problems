package depthFirstTraversal.iterative;

public class MorrisTraversal {

	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);

		new MorrisTraversal().inorder(root);
	}

	public void inorder(Node root) {
		Node current = root;
		while (current != null) {
			if (current.left == null) {
				System.out.print(current.data + " ");
				current = current.right;
			} else {
				Node predecessor = current.left;
				while (predecessor.right != current && predecessor.right != null) {
					predecessor = predecessor.right;
				}
				if (predecessor.right == null) {
					predecessor.right = current;
					current = current.left;
				} else { // left is already visit. Go right after visiting current.
					predecessor.right = null;
					System.out.print(current.data + " ");
					current = current.right;
				}
			}
		}
	}
}