package com.disjoints;

import java.util.ArrayList;
import java.util.List;

public class PredatorDisjointSet {

	public static int minimumGroups(List<Integer> predators) {
		int[] arr = new int[predators.size()];
		int index = 0;
		int result = 0;
		for (Integer val : predators) {
			if (val == -1) {
				arr[index] = 0;
			} else {
				result = makeSet(val, arr, result);
				arr[index] = result;
			}
			index++;
		}
		return 0;
	}

	public static int makeSet(int val, int[] arr, int result) {
		int parent = findParent(val, arr);
		if (parent == 0) {
			return result + 1;
		}
		return result;
	}

	public static int findParent(int val, int[] arr) {
		if (val == 0) {
			return 0;
		}
		return findParent(arr[val], arr);
	}

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(-1);
		list.add(8);
		list.add(6);
		list.add(0);
		list.add(7);
		list.add(3);
		list.add(8);
		list.add(9);
		list.add(-1);
		list.add(6);
		minimumGroups(list);
	}
}
