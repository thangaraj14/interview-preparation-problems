package problems;

import java.util.ArrayList;
import java.util.List;

public class IsValidBST {

	class Node {
		Node left;
		Node right;
		int data;

		public Node(int data) {
			this.data = data;
		}
	}

	Node head;

	public static void main(String[] args) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);

		list.add(5);
		list.add(1);
		list.add(4);
		list.add(2);
		list.add(6);
		list.add(3);

		IsValidBST bst = new IsValidBST();
		System.out.println(bst.isValidBST(list));

	}

	public String isValidBST(List<Integer> a) {
		Node root = null;
		boolean status = a.get(1) > a.get(0) ? true : false;
		for (Integer value : a) {
			Node node = new Node(value);
			if (root == null) {
				root = new Node(a.get(0));
			} else if (root.data < value) {
				Node temp = root;
				Node parent = null;
				while (temp.right != null) {
					parent = temp;
					temp = temp.right;
				}
				if (temp.data < node.data) {
					temp.right = node;
				} else if (temp.data > node.data && parent.data < node.data) {
					temp.left = node;
				} else {
					return "NO";
				}
			} else if (!status) {
				Node temp = root;
				while (temp.left != null) {
					temp = temp.left;
				}
				if (temp.data < node.data && root.data > temp.data) {
					temp.right = node;
				} else if (temp.data > node.data) {
					temp.left = node;
				} else {
					return "NO";
				}
			} else {
				return "NO";
			}
		}
		return "YES";
	}
}
