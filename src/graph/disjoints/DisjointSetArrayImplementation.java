package com.disjoints;

public class DisjointSetArrayImplementation {

	static int[] arr = { -1, -1, -1, -1, -1, -1, -1, -1 };

	public static void main(String[] args) {
		DisjointSetArrayImplementation dsai = new DisjointSetArrayImplementation();
		
		dsai.unionSet(0, 1);
		dsai.unionSet(2, 3);
		dsai.unionSet(4, 5);
		dsai.unionSet(6, 7);
		dsai.unionSet(1, 3);
		dsai.unionSet(5, 6);
		dsai.unionSet(1, 4);
		dsai.unionSet(0, 2);

		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "  ");
		}
	}


	private void unionSet(int src, int dest) {
		src = findParent(src);
		dest = findParent(dest);
		if (src >= 0 && src == dest) {
			System.out.println("found cycle");
		}
		arr[src] = arr[src] - Math.abs(arr[dest]);
		arr[dest] = src;
	}

	private int findParent(int src) {

		if (arr[src] < 0) {
			return src;
		}
		return findParent(arr[src]);
	}
}
