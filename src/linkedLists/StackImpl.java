package linkedLists;

public class StackImpl {

	public void push(Node tail, int data) {
		tail.next = new Node(data);
	}

	public void pop(Node tail, Node root) {
		Node temp = root;
		while (temp.next != null && temp.next.next != null) {
			temp = temp.next;
		}
		temp.next = null;
	}

	public void isEmpty() {
	}

	public void isFull() {

	}
}
