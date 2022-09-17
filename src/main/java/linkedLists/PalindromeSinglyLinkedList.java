package linkedLists;

import java.util.Stack;

public class PalindromeSinglyLinkedList {

	class Node {
		int data;
		Node next;

		public Node(int data) {
			this.data = data;
		}

	}

	public static void main(String[] args) {

		PalindromeSinglyLinkedList palindrome = new PalindromeSinglyLinkedList();
		Node head = palindrome.new Node(1);
		head.next = palindrome.new Node(2);
		head.next.next = palindrome.new Node(3);
		head.next.next.next = palindrome.new Node(2);
		head.next.next.next.next = palindrome.new Node(1);

		System.out.println(palindrome.isPalindrome(head));

	}

	private boolean isPalindrome(Node head) {
		Node slow = head;
		Node fast = head;

		Stack<Node> stack = new Stack<Node>();

		while (fast != null && fast.next != null) {
			stack.push(slow);
			slow = slow.next;
			fast = fast.next.next;
		}

		while (!stack.isEmpty()) {
			Node pop = stack.pop();
			if (pop.data != slow.next.data) {
				return false;
			} else {
				slow = slow.next;
			}
		}
		return true;
	}

}
