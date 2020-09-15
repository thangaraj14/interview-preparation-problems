package linkedLists;

public class ReverseSinglyLinkedListWithoutExtraSpace {
	Node root;

	public static void main(String[] args) {
		ReverseSinglyLinkedListWithoutExtraSpace list = new ReverseSinglyLinkedListWithoutExtraSpace();
		list.root = new Node(1);
		list.root.next = new Node(2);
		list.root.next.next = new Node(3);
		list.root.next.next.next = new Node(4);
		list.root.next.next.next.next = new Node(5);

		reverseLinkedList(list.root);
		
	}

	private static void printNode(Node root) {
		while (root != null) {
			System.out.println(root.data);
			root = root.next;
		}

	}

	private static void reverseLinkedList(Node root) {
		if (null == root) {
			return;
		}
		Node prev = root;
		Node curr = prev.next;
		prev.next = null;

		while (null != prev && null != curr) {
			Node temp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = temp;
		}
		printNode(prev);
	}
}
