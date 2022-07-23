package linkedLists;

public class MiddleElement {

	static SinglyLinkedListNode head;

	public static void main(String[] args) {
		head = new SinglyLinkedListNode(1);
		head.next = new SinglyLinkedListNode(2);
		head.next.next = new SinglyLinkedListNode(3);
		head.next.next.next = new SinglyLinkedListNode(4);
		head.next.next.next.next = new SinglyLinkedListNode(5);

		findMiddleElement();
	}

	private static void findMiddleElement() {
		SinglyLinkedListNode temp = head;
		SinglyLinkedListNode slowPointer = temp;
		SinglyLinkedListNode fastPointer = temp;
		while (fastPointer != null && fastPointer.next != null) {
			slowPointer = slowPointer.next;
			fastPointer = fastPointer.next.next;
		}
		System.out.println(slowPointer.data);
	}

}
