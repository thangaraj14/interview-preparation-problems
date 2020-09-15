package linkedLists;

public class ReversePairs {

	static Node head;

	public static void main(String[] args) {
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);
		head.next.next.next.next.next.next = new Node(7);

		// Node reversePairs = reversePairs(head);
		reverseKNodes(head, null);
		printReversedPairs(ReversePairs.head);
	}

	private static void reverseKNodes(Node node, Node temp) {
		if (node == null)
			return;

		Node curr = node;
		Node prev = null;
		Node next = null;
		int index = 0;

		while (curr != null && index < 3) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
			index++;
		}

		if (temp == null) {
			head = prev;
		} else {
			temp.next = prev;
		}

		if (curr != null)
			reverseKNodes(curr, node);

	}

	private static void printReversedPairs(Node node) {
		if (node == null)
			return;
		while (node != null) {
			System.out.println(node.data);
			node = node.next;
		}

	}

	private static Node reversePairs(Node node) {

		if (node == null || node.next == null) {
			return null;
		}

		// Initialize previous and current pointers
		Node prev = node;
		Node curr = node.next;

		node = curr; // Change head before proceeeding

		// Traverse the list
		while (true) {
			Node next = curr.next;
			curr.next = prev; // Change next of current as previous node

			// If next NULL or next is the last node
			if (next == null || next.next == null) {
				prev.next = next;
				break;
			}

			// Change next of previous to next next
			prev.next = next.next;

			// Update previous and curr
			prev = next;
			curr = prev.next;
		}
		return node;
	}

}
