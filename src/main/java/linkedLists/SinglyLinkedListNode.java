package linkedLists;

public class SinglyLinkedListNode {

	int data;
	SinglyLinkedListNode next;

	public SinglyLinkedListNode(int data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "SinglyLinkedListNode{" +
				"data=" + data+
				'}';
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public SinglyLinkedListNode getNext() {
		return next;
	}

	public void setNext(SinglyLinkedListNode node) {
		this.next = node;
	}

}
