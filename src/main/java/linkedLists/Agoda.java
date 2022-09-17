package linkedLists;

import java.util.Deque;
import java.util.LinkedList;

public class Agoda {

	Node head;

	public static void main(String[] args) {
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);

		Agoda agoda = new Agoda();
		agoda.slidingWindow();
	}

	private void slidingWindow() {
		int[] arr = { 6, 4, 5, 2, 1, 9, 8 };
		int index = 0;
		int k = 3;
		Deque<Integer> deque = new LinkedList<Integer>();
		for (; index < k; index++) {
			while (!deque.isEmpty() && arr[deque.peekLast()] < arr[index])
				deque.removeLast();
			deque.addLast(index);
		}

		for (; index < arr.length; index++) {
			System.out.println(arr[deque.peekFirst()]);
			while (!deque.isEmpty() && index - k >= deque.peekFirst())
				deque.removeFirst();

			while (!deque.isEmpty() && arr[deque.peekLast()] < arr[index])
				deque.removeLast();

			deque.addLast(index);
		}

	}

	private void reverseKNode(Node node, Node link) {

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

		if (link == null)
			head = prev;
		else
			link.next = prev;

		if (curr != null)
			reverseKNode(curr, node);

		while (head != null) {
			System.out.println(head.data);
			head = head.next;
		}
	}

	private void reversePairNodes(Node head) {
		Node temp = null;
		Node realHead = null;
		while (head != null && head.next != null) {
			if (temp != null)
				temp.next.next = head.next;

			temp = head.next;
			head.next = head.next.next;
			temp.next = head;
			if (realHead == null)
				realHead = temp;
			head = head.next;
		}
		while (realHead != null) {
			System.out.println(realHead.data);
			realHead = realHead.next;
		}
	}

	private void findNthNodeFromEnd(Node head, int n) {
		if (head == null || head.next == null)
			return;
		Node slowPointer = head;
		Node fastPointer = head;
		int index = 0;
		while (n > index && fastPointer != null) {
			fastPointer = fastPointer.next;
			index++;
		}

		while (fastPointer.next != null) {
			slowPointer = slowPointer.next;
			fastPointer = fastPointer.next;
		}
		System.out.println(slowPointer.data);
	}

}
