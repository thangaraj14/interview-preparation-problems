package linkedLists;

public class RandomListNode {
	RandomListNode next;
	RandomListNode random;
	double data;

	public RandomListNode(double d) {
		data = d;
	}

	public RandomListNode getNext() {
		return next;
	}

	public void setNext(RandomListNode next) {
		this.next = next;
	}

	public RandomListNode getRandom() {
		return random;
	}

	public void setRandom(RandomListNode random) {
		this.random = random;
	}

	public double getData() {
		return data;
	}

	public void setData(double data) {
		this.data = data;
	}
}
