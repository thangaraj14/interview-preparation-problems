package linkedLists;

public class Node {

	Node left;
	Node right;
	public Node next;
	public int data;
	public Node child;
	public Node prev;

	public Node(int data) {
		this.data = data;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public void setNode(Object o) {
	}

	public Node getNode() {
		return this;
	}
}
