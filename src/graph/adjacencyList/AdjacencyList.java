
package adjacencyList;

import java.util.LinkedList;
import java.util.List;

public class AdjacencyList {

	public static void main(String[] args) {
		List<Integer> adjListArray[] = new LinkedList[5];
		AdjacencyList list = new AdjacencyList();
		list.add(1, 3, adjListArray);
		list.add(1, 2, adjListArray);
		list.add(2, 3, adjListArray);
		list.add(3, 4, adjListArray);
		list.add(4, 2, adjListArray);
		list.add(5, 4, adjListArray);

		list.printList(adjListArray);
	}

	private void printList(List<Integer>[] adjListArray) {
		for (int i = 0; i < adjListArray.length; i++) {
			List<Integer> list = adjListArray[i];
			System.out.print((i + 1) + "-->");
			boolean firstTime = true;
			for (Integer val : list) {
				if (!firstTime)
					System.out.print(" - ");
				else
					firstTime = false;
				System.out.print(val);
			}
			System.out.println();
		}
	}

	private void add(int src, int dest, List<Integer>[] adjListArray) {
		int val = src - 1;
		List<Integer> list = adjListArray[val];
		if (null == list)
			list = new LinkedList<Integer>();
		list.add(dest);
		adjListArray[val] = list;
	}

}
