package problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ForestDeleteNodeInTree {

	public static void main(String[] args) {
		int[] toDelete = { 6, 5 };

		TNode root = new TNode(2);

		root.left = new TNode(7);
		root.right = new TNode(5);

		root.left.left = new TNode(12);
		root.left.right = new TNode(6);

		root.right.right = new TNode(9);
		root.right.left = new TNode(4);

		delNodes(root, toDelete);
	}

	static Set<Integer> to_delete_set = new HashSet<>();
	static List<TNode> res = new ArrayList<>();

	public static List<TNode> delNodes(TNode root, int[] to_delete) {
		for (int i : to_delete)
			to_delete_set.add(i);
		helper(root, true);
		return res;
	}

	private static TNode helper(TNode node, boolean is_root) {
		if (node == null)
			return null;
		boolean deleted = to_delete_set.contains(node.val);
		if (is_root && !deleted)
			res.add(node);
		node.left = helper(node.left, deleted);
		node.right = helper(node.right, deleted);
		return deleted ? null : node;
	}
}

class TNode {
	TNode left;
	TNode right;
	int val;

	public TNode(int data) {
		this.val = data;
	}

	public String toString() {
		return this.val + "";
	}
}
