package linkedLists;

public class RandomNode {
	RandomNode next;
	RandomNode random;
	double data;

	public RandomNode(double d) {
		data = d;
	}

	public RandomNode getNext() {
		return next;
	}

	public void setNext(RandomNode next) {
		this.next = next;
	}

	public RandomNode getRandom() {
		return random;
	}

	public void setRandom(RandomNode random) {
		this.random = random;
	}

	public double getData() {
		return data;
	}

	public void setData(double data) {
		this.data = data;
	}
}
