package problems.reverseorder;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Node {
	int data;
	Node left;
	Node right;

	public Node(int data) {
		this.data = data;
	}
}

public class PrintReverseOrder {

	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.right = new Node(4);
		root.right.left = new Node(5);

		print(printReverseOrder(root));
	}

	private static void print(Stack<Node> printReverseOrder) {
		while (!printReverseOrder.isEmpty()) {
			System.out.println(printReverseOrder.pop().data);
		}

	}

	private static Stack<Node> printReverseOrder(Node root) {
		if (root == null)
			return null;

		Queue<Node> queue = new LinkedList<Node>();
		Stack<Node> stack = new Stack<Node>();
		Node node = root;
		queue.add(node);

		while (!queue.isEmpty()) {
			node = queue.remove();

			if (node.right != null) {
				queue.add(node.right);
			}
			if (node.left != null) {
				queue.add(node.left);
			}
			stack.push(node);
		}
		return stack;
	}

}
