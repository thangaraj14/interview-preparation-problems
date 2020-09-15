package breadthFirstSearch;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {

	public static void main(String[] args) {
		BreadthFirstSearch bfs = new BreadthFirstSearch();
		Graph graph = new Graph(5);
		bfs.addEdges(1, 2, graph);
		bfs.addEdges(2, 3, graph);
		bfs.addEdges(3, 4, graph);
		bfs.addEdges(4, 5, graph);
		bfs.addEdges(2, 4, graph);
		bfs.addEdges(1, 5, graph);

		bfs.print(1, graph);

	}

	private void print(int i, Graph graph) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(i);
		Queue<Integer> clone = new LinkedList<Integer>(queue);
		exploreVertex(queue, clone, graph);

		for (Integer val : clone) {
			System.out.print(val + 1 + "->");
		}
	}

	private void exploreVertex(Queue<Integer> queue, Queue<Integer> clone, Graph graph) {
		while (!queue.isEmpty()) {
			Integer poll = queue.poll();
			List<Integer> list = graph.adjacentList[poll];
			for (Integer value : list) {
				if (!clone.contains(value)) {
					clone.add(value);
					queue.add(value);
				}
			}
		}
	}

	private void addEdges(int src, int dest, Graph graph) {
		int srcVal = src - 1;
		int destVal = dest - 1;
		graph.adjacentList[srcVal].add(destVal);
		graph.adjacentList[destVal].add(srcVal);
	}
}
