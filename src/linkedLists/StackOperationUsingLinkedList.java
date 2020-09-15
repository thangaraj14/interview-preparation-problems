package linkedLists;

public class StackOperationUsingLinkedList {

	static Node head;

	public static void main(String[] args) {
		push(5);
		push(4);
		push(3);
		printList();
		pop();
		printList();
	}

	private static void pop() {
		Node temp = head;
		head = temp.getNode();
	}

	private static void printList() {
		Node temp = head;
		while (null != temp.getNode()) {
			System.out.println(temp.getData());
			temp = temp.getNode();
		}
		System.out.println(temp.getData());

	}

	private static void push(int data) {
		Node node = new Node(data);
		if (null == head) {
			head = node;
		} else {
			node.setNode(head);
			head = node;
		}
	}

}
