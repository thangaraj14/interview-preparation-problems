package linkedLists;

public class DLLToBBST {

	static Node head;

	public static void main(String[] args) {
		addNode(7);
		addNode(6);
		addNode(5);
		addNode(4);
		addNode(3);
		addNode(2);
		addNode(1);

		DLLToBBST bst = new DLLToBBST();
		bst.preOrder(bst.sortedListToBST());
	}

	Node sortedListToBST() {
		int n = countNodes(head);
		return sortedListToBSTRecur(n);
	}

	Node sortedListToBSTRecur(int n) {
		if (n <= 0)
			return null;
		Node left = sortedListToBSTRecur(n / 2);
		Node root = head;
		root.prev = left;
		head = head.next;
		root.next = sortedListToBSTRecur(n - n / 2 - 1);

		return root;
	}

	private static void addNode(int i) {

		Node node = new Node(i);
		if (null == head) {
			head = node;
		} else {
			Node temp = head;
			while (null != temp.next) {
				temp = temp.next;
			}
			temp.next = node;
			node.prev = temp;
		}
	}

	int countNodes(Node head) {
		int count = 0;
		Node temp = head;
		while (temp != null) {
			temp = temp.next;
			count++;
		}
		return count;
	}

	void preOrder(Node node) {
		if (node == null)
			return;
		System.out.print(node.data + " ");
		preOrder(node.prev);
		preOrder(node.next);
	}
}
