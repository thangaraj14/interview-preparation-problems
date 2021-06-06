package problems.sumofbranch;

class Node {
	int data;
	Node left;
	Node right;

	public Node(int val) {
		data = val;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

}

public class SumOfBranch {

	static Node root;

	public static void main(String[] args) {

		root = new Node(50);
		root.left = new Node(30);
		root.left.left = new Node(20);
		root.left.right = new Node(40);

		root.right = new Node(70);
		root.right.right = new Node(80);
		root.right.left = new Node(60);

		Node temp = root;
		removeBranch(temp, 120, 0);

	}

	private static Node removeBranch(Node temp, int i, int sum) {
		if (null == temp)
			return null;

		if ( null==removeBranch(temp.left, i, temp.data + sum) && null==removeBranch(temp.right, i, temp.data + sum) && temp.data + sum < i) {
			System.out.println(temp.data);
		}
		return temp;
	}

}
