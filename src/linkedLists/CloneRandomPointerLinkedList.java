package linkedLists;

import java.util.HashMap;
import java.util.Map;

// Java program to clone a linked list with next
// and arbit pointers in O(n) time 
class CloneRandomPointerLinkedList {

	// Structure of linked list Node
	static class Node {
		int data;
		Node next, random;

		Node(int x) {
			data = x;
			next = random = null;
		}
	}

	// Utility function to print the list.
	static void print(Node start) {
		Node ptr = start;
		while (ptr != null) {
			System.out.println("Data = " + ptr.data + ", Random = " + ptr.random.data);
			ptr = ptr.next;
		}
	}

	// This function clones a given
	// linked list in O(1) space
	static Node clone(Node start) {
		if(start==null) return null;
		Node curr = start, temp = null;

		// insert additional node after
		// every node of original list
		while (curr != null) {
			temp = curr.next;

			// Inserting node
			curr.next = new Node(curr.data);
			curr.next.next = temp;
			curr = temp;
		}
		curr = start;

		// adjust the random pointers of the
		// newly added nodes
		while (curr != null && curr.next!=null) {
			if (curr.random != null)
				curr.next.random =  curr.random.next;
			// move to the next newly added node by
			// skipping an original node
			curr =curr.next.next;
		}

		Node original = start, copy = start.next;

		// save the start of copied linked list
		temp = copy;

		// now separate the original list and copied list
		while (original != null && copy != null) {
			original.next = (original.next != null) ? original.next.next : original.next;

			copy.next = (copy.next != null) ? copy.next.next : copy.next;
			original = original.next;
			copy = copy.next;
		}
		return temp;
	}

	public RandomListNode copyRandomList(RandomListNode head) {
		if (head == null) {
			return null;
		}

		final Map<RandomListNode, RandomListNode> map = new HashMap<>();

		RandomListNode cur = head;
		while(cur != null) {
			map.put(cur, new RandomListNode(cur.data));
			cur = cur.next;
		}

		for (Map.Entry<RandomListNode, RandomListNode> entry : map.entrySet()) {
			final RandomListNode newNode = entry.getValue();
			newNode.next = map.get(entry.getKey().next);
			newNode.random = map.get(entry.getKey().random);
		}

		return map.get(head);
	}

	// Driver code
	public static void main(String[] args) {
		Node start = new Node(1);
		start.next = new Node(2);
		start.next.next = new Node(3);
		start.next.next.next = new Node(4);
		start.next.next.next.next = new Node(5);

		// 1's random points to 3
		start.random = start.next.next;

		// 2's random points to 1
		start.next.random = start;

		// 3's and 4's random points to 5
		start.next.next.random = start.next.next.next.next;
		start.next.next.next.random = start.next.next.next.next;

		// 5's random points to 2
		start.next.next.next.next.random = start.next;

		System.out.println("Original list : ");
		print(start);

		System.out.println("Cloned list : ");
		Node cloned_list = clone(start);
		print(cloned_list);

	}
}
