package topologicalsort;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TopologicalSortList {

	class Graph {
		List<Integer> adjacentList[];
		int vertices;

		public Graph(int vertices) {
			this.vertices = vertices;
			adjacentList = new LinkedList[vertices];

			for (int i = 0; i < vertices; i++) {
				adjacentList[i] = new LinkedList<Integer>();
			}
		}
	}

	public static void main(String[] args) {
		TopologicalSortList ts = new TopologicalSortList();
		Graph graph = ts.new Graph(8);
		ts.addEdge(0, 2, graph);
		ts.addEdge(1, 2, graph);
		ts.addEdge(1, 3, graph);
		ts.addEdge(2, 4, graph);
		ts.addEdge(4, 5, graph);
		ts.addEdge(4, 7, graph);
		ts.addEdge(5, 6, graph);

		Stack<Integer> stack = new Stack<Integer>();
		Stack<Integer> visited = new Stack<Integer>();

		for (int i = 0; i < graph.vertices; i++) {
			if (!visited.contains(i))
				ts.topologicalSort(graph, i, stack, visited);
		}

		stack.forEach(System.out::println);
	}

	private void topologicalSort(Graph graph, int i, Stack<Integer> stack, Stack<Integer> visited) {
		if (visited.contains(i)) {
			return;
		}
		visited.push(i);
		List<Integer> list = graph.adjacentList[i];
		if (list.isEmpty()) {
			stack.push(i);
		}
		for (Integer node : list) {
			topologicalSort(graph, node, stack, visited);
		}
		if (!list.isEmpty()) {
			stack.push(i);
		}
	}

	private void addEdge(int i, int j, Graph graph) {
		graph.adjacentList[i].add(j);
	}
}
