package depthFirstTraversal.iterative;

public class Node {

	Node left;
	Node right;
	int data;

	public Node(int value) {
		this.data = value;
	}
	
	public String toString() {
		return this.data + "";
	}
}
