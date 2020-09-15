package graph.interview;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet {

	private Map<Long, Node> map = new HashMap<>();

	class Node {
		long data;
		Node parent;
		int rank;
	}

	/**
	 * Create a set with only one element.
	 */
	public void makeSet(long data) {
		Node node = new Node();
		node.data = data;
		node.parent = node;
		node.rank = 0;
		map.put(data, node);
	}

	/**
	 * Combines two sets together to one. Does union by rank
	 *
	 * @return true if data1 and data2 are in different set before union else false.
	 */
	public boolean union(long data1, long data2) {
		Node node1 = map.get(data1);
		Node node2 = map.get(data2);

		Node parent1 = findParent(node1);
		Node parent2 = findParent(node2);

		// if they are part of same set do nothing
		if (parent1.data == parent2.data) {
			return false;
		}

		// else whoever's rank is higher becomes parent of other
		if (parent1.rank >= parent2.rank) {
			// increment rank only if both sets have same rank
			parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
			parent2.parent = parent1;
		} else {
			parent1.parent = parent2;
		}
		return true;
	}

	/**
	 * Finds the representative of this set
	 */
	public long findParent(long data) {
		return findParent(map.get(data)).data;
	}

	/**
	 * Find the representative recursively and does path compression as well.
	 */
	private Node findParent(Node node) {
		Node parent = node.parent;
		if (parent == node) {
			return parent;
		}
		node.parent = findParent(node.parent);
		return node.parent;
	}

	public static void main(String args[]) {
		DisjointSet ds = new DisjointSet();
		ds.makeSet(1);
		ds.makeSet(2);
		ds.makeSet(3);
		ds.makeSet(4);
		ds.makeSet(5);
		ds.makeSet(6);
		ds.makeSet(7);

		ds.union(1, 2);
		ds.union(2, 3);
		ds.union(4, 5);
		ds.union(6, 7);
		ds.union(5, 6);
		ds.union(3, 7);

		System.out.println(ds.findParent(1));
		System.out.println(ds.findParent(2));
		System.out.println(ds.findParent(3));
		System.out.println(ds.findParent(4));
		System.out.println(ds.findParent(5));
		System.out.println(ds.findParent(6));
		System.out.println(ds.findParent(7));
	}

	
}

 class DisjointSetLeetcode {

	 private int[] parent;
	 private byte[] rank;

	 public DisjointSetLeetcode(int n) {
		 if (n < 0) throw new IllegalArgumentException();
		 parent = new int[n];
		 rank = new byte[n];
	 }

	 public int find(int x) {
		 if (parent[x] == 0) return x;
		 return parent[x] = find(parent[x]); // Path compression by halving.
	 }

	 // Return false if x, y are connected.
	 public boolean union(int x, int y) {
		 int rootX = find(x);
		 int rootY = find(y);
		 if (rootX == rootY) return false;

		 // Make root of smaller rank point to root of larger rank.
		 if (rank[rootX] < rank[rootY]) parent[rootX] = rootY;
		 else if (rank[rootX] > rank[rootY]) parent[rootY] = rootX;
		 else {
			 parent[rootX] = rootY;
			 rank[rootY]++;
		 }

		 return true;
	 }
 }

class UnionFind {
	int[] sets;
	int[] size;
	int count;

	public UnionFind(int n) {
		sets = new int[n];
		size = new int[n];
		count = n;

		for (int i = 0; i < n; i++) {
			sets[i] = i;
			size[i] = 1;
		}
	}

	public int find(int node) {
		while (node != sets[node]) {
			node = sets[node];
		}
		return node;
	}

	public void union(int i, int j) {
		int node1 = find(i);
		int node2 = find(j);

		if (node1 == node2) {
			return;
		}

		if (size[node1] < size[node2]) {
			sets[node1] = node2;
			size[node2] += size[node1];
		}
		else {
			sets[node2] = node1;
			size[node1] += size[node2];
		}
		--count;
	}
}