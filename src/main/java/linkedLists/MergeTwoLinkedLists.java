package linkedLists;

public class MergeTwoLinkedLists {

	public static Node head_1;
	public static Node head_2;
	public static Node head;

	public static void main(String[] args) {
		addNodeInList1();
		addNodeInList2();
		printLst();
		mergeLists();
		printList();
	}

	private static void printList() {
		Node temp = head;

		while (temp != null) {
			System.out.println(temp.getData());
			temp = temp.getNode();
		}

	}

	private static void mergeLists() {
		Node temp_1 = head_1;
		Node temp_2 = head_2;
		Node temp = head;

		while (null != temp_1 && temp_2 != null) {
			if (temp_1.getData() < temp_2.getData()) {
				temp = insertNode(temp_1.getData(), temp);
				temp_1 = temp_1.getNode();
			} else {
				temp = insertNode(temp_2.getData(), temp);
				temp_2 = temp_2.getNode();
			}
		}
		while (temp_1 != null && temp_2 == null) {
			Node node = new Node(temp_1.getData());
			temp.setNode(node);
			temp = temp.getNode();
			temp_1 = temp_1.getNode();
		}
		while (temp_2 != null && temp_1 == null) {
			Node node = new Node(temp_2.getData());
			temp.setNode(node);
			temp = temp.getNode();
			temp_2 = temp_2.getNode();
		}
	}

	private static Node insertNode(int data, Node temp) {
		Node node = new Node(data);
		if (temp == null) {
			head = node;
			temp = node;
		} else {
			temp.setNode(node);
			temp = temp.getNode();
		}
		return temp;
	}

	private static void printLst() {

		Node temp_1 = head_1;
		while (temp_1 != null) {
			System.out.print(temp_1.getData() + " ");
			temp_1 = temp_1.getNode();
		}

		System.out.println();

		Node temp = head_2;
		while (temp != null) {
			System.out.print(temp.getData() + " ");
			temp = temp.getNode();
		}
		System.out.println();
		System.out.println();
	}

	private static void addNodeInList2() {
		int n = 5;
		Node node = new Node(2);
		head_1 = node;
		Node temp = head_1;
		for (int i = 0; i < 5; i++) {
			Node newNode = new Node(n);
			temp.setNode(newNode);
			temp = newNode;
			n += 2;
		}
	}

	private static void addNodeInList1() {
		int n = 2;
		Node node = new Node(1);
		head_2 = node;
		Node temp = head_2;
		for (int i = 0; i < 5; i++) {
			Node newNode = new Node(n);
			temp.setNode(newNode);
			temp = newNode;
			n += 3;
		}
	}
}
