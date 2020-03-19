package geeksforgeeks;

class LinkedListRemoveDuplicate{
    /**
	 * Method to remove duplicates from Linked list
	 * when no additional buffer is allowed.
	 * 
	 * Time Complexity : O(n^2)
	 * Space Complexity : O(1)
	 * 
	 * @param head
	 */
	public static <T> void removeDuplicatesWithoutBuffer(LinkedListNode<T> head) {
		/* If head is null, stop processing */
		if (head == null) {
			return;
		}
		/* We will need two pointers here i.e current and runner.
		 * When current is pointing to a node, move runner through
		 * rest of the list, checking for duplicates */
		LinkedListNode<T> current = head;
		while (current != null) {
			/* Have runner point to current node */
			LinkedListNode<T> runner = current;
			while (runner.next != null) {
				/* If it is duplicate, jump runner over the node */
				if (runner.next.data == current.data) {
					runner.next = runner.next.next;
				} else {
					runner = runner.next; 
				}
			}
			current = current.next;
		}
	}
}