package depthFirstTraversal.recursion;

public class DepthFirstTraversal {

	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);

		treeTraversal(root);
	}

	private static void treeTraversal(Node root) {
		preOrderTraversal(root);
		System.out.println();
		inOrderTraversal(root);
		System.out.println();
		postOrderTraversal(root);

	}

	private static void preOrderTraversal(Node root) {
		
		if(null==root)
			return;
		
		System.out.print(root.data+" ");
		preOrderTraversal(root.left);
		preOrderTraversal(root.right);
		
	}

	private static void inOrderTraversal(Node root) {

		if (root == null)
			return;

		inOrderTraversal(root.left);
		System.out.print(root.data +" ");
		inOrderTraversal(root.right);

	}
	
private static void postOrderTraversal(Node root) {
		
		if(null==root)
			return;
		
		preOrderTraversal(root.left);
		preOrderTraversal(root.right);
		System.out.print(root.data+" ");
		
	}

}
