package basic.recursion.levelorder.traversal;

public class BinaryTree {
	Node root;

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.right.left = new Node(4);
		tree.root.right.right = new Node(5);

		tree.levelOrderTraversal();
	}

	private void levelOrderTraversal() {

		int height = heightOfTree(root);

		for (int i = 1; i <= height; i++) {
			printLevelOrder(root, i);
		}

	}

	private void printLevelOrder(Node root, int level) {
		if (root == null) {
			return;
		} else if (level == 1) {
			System.out.print(root.data +" ");
		} else if (level > 1) {
			printLevelOrder(root.left, level - 1);
			printLevelOrder(root.right, level - 1);
		}
	}

	private int heightOfTree(Node root) {
		if (root == null) {
			return 0;
		}

		int lheight = heightOfTree(root.left);
		int rHeight = heightOfTree(root.right);

		if (lheight > rHeight)
			return lheight + 1;
		return rHeight + 1;
	}

}
