package linkedLists;

/**
 * https://www.geeksforgeeks.org/top-20-linked-list-interview-question/
 *
 */
public class Top20LinkedListQuestions {

	static Node head;

	private Top20LinkedListQuestions() {
		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);
		head.next.next.next.next.next.next = new Node(7);
		head.next.next.next.next.next.next.next = new Node(8);
		head.next.next.next.next.next.next.next.next = new Node(9);
		head.next.next.next.next.next.next.next.next.next = new Node(10);
	}

	public static void main(String[] args) {
		Top20LinkedListQuestions list = new Top20LinkedListQuestions();
		// Node middleElement = list.printMiddleElement();
		// System.out.println(middleElement.data);
		// list.flattenLinkedList();
		// list.removeDuplicatesFromSortedLinkedList();
		// list.add1ToLinkedList();
		Node reverseHead = list.reversePair(head);
		printList(reverseHead);
		/*
		 * Node root = list.reverseKNodes(head, 3);
		 * 
		 */
	}

	private static void printList(Node root) {
		while (root != null) {
			System.out.println(root.data);
			root = root.next;
		}

	}

	private Node reversePair(Node head) {
		Node temp;
		if (head == null || head.next == null) {
			return null;
		}

		temp = head.next;
		head.next = temp.next;
		temp.next = head;
		head = temp;

		head.next.next = reversePair(head.next.next);

		return head;

	}

	private Node reverseKNodes(Node root, int k) {
		Node curr = root;
		Node next = null;
		Node prev = null;
		int index = 0;
		while (k > index && curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
			index++;
		}

		if (curr != null) {
			root.next = reverseKNodes(curr, k);
		}

		return prev;
	}

	private void add1ToLinkedList() {
		Node curr = reverseList(head);
		int carry = 1;
		int sum = 0;
		Node temp = null;
		// https://www.geeksforgeeks.org/add-1-number-represented-linked-list/

	}

	private Node reverseList(Node curr) {
		Node prev = null, next;

		while (curr.next != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return curr;
	}

	private void removeDuplicatesFromSortedLinkedList() {
		Node head = getDuplicateList();
		Node next = null;
		Node curr = head;

		while (curr.next != null) {
			next = curr.next;
			if (next.data == curr.data) {
				curr.next = next.next;
			} else {
				curr = curr.next;
			}
		}

		while (head.next != null) {
			System.out.println(head.data);
			head = head.next;
		}
		System.out.println(head.data);
	}

	private Node getDuplicateList() {
		Node head = new Node(1);
		head.next = new Node(1);
		head.next.next = new Node(1);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);
		head.next.next.next.next.next.next = new Node(6);
		head.next.next.next.next.next.next.next = new Node(8);
		head.next.next.next.next.next.next.next.next = new Node(8);
		head.next.next.next.next.next.next.next.next.next = new Node(10);
		return head;
	}

	private void flattenLinkedList() {
		FlattenLinkedList.flattenList();
	}

	private Node printMiddleElement() {

		if (head == null) {
			return null;
		}
		Node slowPointer = head;
		if (head.next == null) {
			return head;
		}
		Node fastPointer = head.next;

		while (fastPointer != null && fastPointer.next != null) {
			slowPointer = slowPointer.next;
			fastPointer = fastPointer.next.next;
		}
		return slowPointer;
	}

}
