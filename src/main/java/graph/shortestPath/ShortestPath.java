package graph.shortestPath;

import java.util.LinkedList;

public class ShortestPath {

	class Graph {
		int vertices;
		LinkedList<Integer> adjacentList[];

		public Graph(int vertices) {
			this.vertices = vertices;
			adjacentList = new LinkedList[vertices];

			for (int i = 0; i < vertices; i++) {
				adjacentList[i] = new LinkedList<Integer>();
			}
		}

		void addEdge(int src, int dest) {
			adjacentList[src].add(dest);
			adjacentList[dest].add(src);
		}
	}

	public static void main(String[] args) {
		ShortestPath sp = new ShortestPath();
		Graph graph = sp.new Graph(8);
		graph.addEdge(1, 2);
		graph.addEdge(1, 0);
		graph.addEdge(0, 3);
		graph.addEdge(7, 3);
		graph.addEdge(7, 4);
		graph.addEdge(4, 5);
		graph.addEdge(6, 5);
		graph.addEdge(3, 4);

		LinkedList<Integer> visited = new LinkedList<Integer>();
		sp.shortestPath(0, 7, graph, visited);

	}

	private void shortestPath(int src, int dest, Graph graph, LinkedList<Integer> visited) {

		LinkedList<Integer> linkedList = graph.adjacentList[src];
		if (visited.contains(src) || linkedList.isEmpty() ) {
			return;
		}

		visited.add(src);
		for (Integer val : linkedList) {
			if (dest == val) {
				System.out.println(val);
				return;
			}
			shortestPath(val, dest, graph,visited);
		}
	}

}
