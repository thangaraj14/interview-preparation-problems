package basic.queue.levelorder.traversal;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

	Node root;

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.right.left = new Node(4);
		tree.root.right.right = new Node(5);

		tree.printTraversal();

	}

	private void printTraversal() {

		int height = heightOfTree(root);

		printLevelOrder(root);

	}

	private void printLevelOrder(Node root) {

		if (root == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node temp = queue.remove();
			System.out.print(temp.data + " ");
			if (null != temp.left)
				queue.add(temp.left);
			if (null != temp.right)
				queue.add(temp.right);

		}

	}

	private int heightOfTree(Node root) {

		if (root == null)
			return 0;

		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		int height = 0;

		while (true) {

			int nodeCount = queue.size();

			if (nodeCount == 0)
				return height;
			height++;

			while (nodeCount > 0) {
				Node tempRoot = queue.remove();
				if (null != tempRoot.left)
					queue.add(tempRoot.left);
				if (null != tempRoot.right)
					queue.add(tempRoot.right);
				nodeCount--;
			}
		}

	}
}
