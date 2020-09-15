package linkedLists;

public class AllProblems {

	static Node head;
	static int size;
	static RandomNode randomHead;

	public static void main(String[] args) {
		addNode(2);
		addNode(5);
		addNode(8);
		addNode(10);
		addNode(12);
		// findNthNode(3);
		// findCyclic();
		// reverseLinkedList();
		// middleOfTheLinkedList();
		// printListInReverse(head);
		// reversePairLinkedListIterative();
		// reverseLinkedListUsingRecursiveMethod(head);
		// cloneRandomLinkedList();
		// printList();
		// reverseLinkedListPractise();
		Node head2 = new Node(1);
		head2.next = new Node(4);
		head2.next.next = new Node(7);
		head2.next.next = new Node(9);
		head2.next.next.next = new Node(19);
		head2.next.next.next.next = new Node(29);
		Node result = mergeList(head, head2);

		while (result != null) {
			System.out.print(result.data + " ");
			result = result.next;
		}
	}

	private static Node mergeList(Node a, Node b) {
		System.out.println(a + "->" + b);
		Node c = null;
		if (a == null)
			return b;
		if (b == null)
			return a;

		if (a.data < b.data) {
			c = a;
			c.next = mergeList(a.next, b);
		} else {
			c = b;
			c.next = mergeList(b.next, a);
		}
		return c;
	}

	private static void cloneRandomLinkedList() {

		RandomNode node1 = new RandomNode(1);
		RandomNode node2 = new RandomNode(2);
		RandomNode node3 = new RandomNode(3);
		RandomNode node4 = new RandomNode(4);
		RandomNode node5 = new RandomNode(5);

		randomHead = node1;

		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;

		node1.random = node3;
		node2.random = node1;
		node3.random = node5;
		node4.random = node3;
		node5.random = node2;

		// create cloned node between original nodes
		RandomNode temp = randomHead;
		while (null != temp) {
			RandomNode rn = new RandomNode(temp.data + 0.5);
			rn.next = temp.next;
			temp.next = rn;
			temp = rn.next;
		}
		RandomNode assignTemp = randomHead;
		while (null != assignTemp) {
			assignTemp.next.random = assignTemp.random.next;

			assignTemp = assignTemp.next;
		}

		temp = randomHead;
		while (null != temp && null != temp.next) {
			assignTemp.next.next = assignTemp.next.next.next;

		}

		RandomNode tempp = randomHead;
		while (tempp != null) {
			System.out.println(tempp.data);
			tempp = tempp.next;
		}

	}

	private static Node reverseLinkedListUsingRecursiveMethod(Node curr) {

		if (null == curr)
			return null;

		Node prev = reverseLinkedListUsingRecursiveMethod(curr.next);
		if (null != prev)
			prev.next = curr;
		else
			head = curr;
		return curr;

	}

	private static void reversePairLinkedListIterative() {

		Node temp1 = null;
		Node realHead = null;
		while (head != null && head.next != null) {
			if (temp1 != null)
				temp1.next.next = head.next;

			temp1 = head.next;
			head.next = head.next.next;
			temp1.next = head;
			if (realHead == null)
				realHead = temp1;
			head = head.next;
		}
		while (realHead != null) {
			System.out.println(realHead.data);
			realHead = realHead.next;
		}
	}

	private static Object printListInReverse(Node temp) {
		if (temp != null) {
			printListInReverse(temp.getNode());
			System.out.println(temp.getData());
		}
		return null;
	}

	private static void middleOfTheLinkedList() {

		Node fastPointer = head;
		Node slowPointer = head;
		while (fastPointer.getNode() != null && fastPointer.getNode().getNode() != null) {
			slowPointer = slowPointer.getNode();
			fastPointer = fastPointer.getNode().getNode();
			if (fastPointer.getNode() == null || fastPointer.getNode().getNode() == null) {
				System.out.println(slowPointer.getData());
			}
		}
	}

	private static void reverseLinkedList() {
		Node curr = head;
		Node prev = curr;
		if (head.getNode() != null) {
			curr = curr.getNode();
		}
		while (curr.getNode() != null) {
			Node next = curr.getNode();
			curr.setNode(prev);
			prev = curr;
			curr = next;
		}
		curr.setNode(prev);
		head = curr;
	}

	private static void reverseLinkedListPractise() {
		Node curr = head;
		Node prev = null;
		Node next = null;
		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		curr = prev;
		while (curr != null) {
			System.out.println(curr.data);
			curr = curr.next;
		}
	}

	private static void findCyclic() {
		Node slowPointer = head;
		Node fastPointer = head;
		System.out.println("slow pointer : " + slowPointer.getData() + "and Fast pointer :" + fastPointer.getData());
		while (fastPointer.getNode() != null && fastPointer.getNode().getNode() != null) {
			slowPointer = slowPointer.getNode();
			fastPointer = fastPointer.getNode().getNode();
			if (slowPointer == fastPointer) {
				System.out.println("Gotcha its cyclic" + slowPointer + "::" + fastPointer);
				return;
			} else {
				System.out.println("slow pointer : " + slowPointer + "and Fast pointer :" + fastPointer);
			}
		}
	}

	private static void findNthNode(int n) {
		Node temp = head;
		Node nthNode = head;
		int index = 1;
		while (index < n) {
			temp = temp.getNode();
			index++;
		}

		while (temp.getNode() != null) {
			temp = temp.getNode();
			nthNode = nthNode.getNode();
		}

		System.out.println("Nth Node from end: " + nthNode.getData());

	}

	private static void printList() {
		Node temp = head;
		int index = 0;
		while (index < size) {
			System.out.println(temp.getData());
			temp = temp.getNode();
			index++;
		}
	}

	private static void addNode(int data) {
		Node node = new Node(data);
		if (head == null) {
			head = node;
			node.setNode(null);
		} else {
			Node temp = head;
			int index = 1;
			while (index < size) {
				temp = temp.getNode();
				index++;
			}
			temp.setNode(node);
			node.setNode(null);
		}
		size++;
	}
}
