package linkedLists;

public class ReverseKBlockNode {

	static SinglyLinkedListNode head;
	static int k = 3;

	public static void main(String[] args) {
		head = new SinglyLinkedListNode(1);
		head.next = new SinglyLinkedListNode(2);
		head.next.next = new SinglyLinkedListNode(3);
		head.next.next.next = new SinglyLinkedListNode(4);
		head.next.next.next.next = new SinglyLinkedListNode(5);
		head.next.next.next.next.next = new SinglyLinkedListNode(6);
		head.next.next.next.next.next.next = new SinglyLinkedListNode(7);
		head.next.next.next.next.next.next.next = new SinglyLinkedListNode(8);
		head.next.next.next.next.next.next.next.next = new SinglyLinkedListNode(9);
		head.next.next.next.next.next.next.next.next.next = new SinglyLinkedListNode(10);

		reverseBlocks(head, null);
		print(head);
	}

	private static void reverseBlocks(SinglyLinkedListNode temp, SinglyLinkedListNode link) {

		SinglyLinkedListNode current = temp;
		SinglyLinkedListNode next = null;
		SinglyLinkedListNode prev = null;
		int count = 0;
		while (current != null && count < k) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
			count++;
		}
		if (link != null) {
			link.next = prev;
		} else {
			head = prev;
		}
		if (current != null) {
			reverseBlocks(current, temp);
		}

	}

	private static void print(SinglyLinkedListNode current) {
		SinglyLinkedListNode node = current;
		while (node != null) {
			System.out.println(node.data);
			node = node.next;
		}
	}

	public ListNode reverseKNodes(ListNode head, int k){
		if(head==null) return head;
		ListNode root= head;
		int count=0;
		while (count<k){ // && root!=null add this condition to reverse remaining elements
			root=root.next;
			count++;
		}

		ListNode prev= reverseKNodes(root, k); //prev node point to the the answer of sub-problem
		while (count>0){
			ListNode next= head.next;
			head.next=prev;
			prev=head;
			head=next;
			count--;

		}

		return prev;
	}

}
