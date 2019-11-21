package geeksforgeeks;

class LLNode {
	public int val;
	public LLNode next;
	public LLNode random;

	public LLNode() {
	}

	public LLNode(int _val, LLNode _next, LLNode _random) {
		val = _val;
		next = _next;
		random = _random;
	}

	public String toString() {
		return "" + this.val;
	}
};

class RandomLinkedList {
	public LLNode copyRandomList(LLNode head) {
		if (head == null)
			return null;
		LLNode temp = head;
		while (temp != null) {
			LLNode LLNode = new LLNode(temp.val + 10, temp.next, null);
			temp.next = LLNode;
			temp = temp.next.next;
		}
		LLNode randomLLNode = head;
		while (randomLLNode != null) {
			randomLLNode.next.random = randomLLNode.random.next;
			randomLLNode = randomLLNode.next.next;
		}
		LLNode copyHead = head.next;
		LLNode tempHead = copyHead;
		while (head != null && tempHead != null) {
			head.next = head.next == null || head.next.next == null ? head.next : head.next.next;
			tempHead.next = tempHead.next == null || tempHead.next.next == null ? tempHead.next : tempHead.next.next;
			head = head.next;
			tempHead = tempHead.next;
		}
		return copyHead;
	}

	public static void main(String[] args) {
		LLNode head = new LLNode(1, null, null);
		head.next = new LLNode(2, null, null);
		head.next.next = new LLNode(3, null, null);
		head.next.next.next = new LLNode(4, null, null);
		head.random = head.next.next;
		head.next.random = head.next.next.next;
		head.next.next.random = head.next;
		head.next.next.next.random = head;
		RandomLinkedList solution = new RandomLinkedList();
		solution.copyRandomList(head);
	}
}
