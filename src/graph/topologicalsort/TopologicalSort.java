package topologicalsort;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Space and time complexity is O(n).
 */
public class TopologicalSort<T> {

	public Stack<Vertex<T>> topologicalSort(Graph<T> graph) {
		Stack<Vertex<T>> stack = new Stack<>();
		Set<Vertex<T>> visited = new HashSet<>();

		for (Vertex<T> vertex : graph.getAllVertex()) {
			if (!visited.contains(vertex)) {
				topSortUtil(stack, visited, vertex);
			}
		}
		return stack;
	}

	private void topSortUtil(Stack<Vertex<T>> stack, Set<Vertex<T>> visited, Vertex<T> vertex) {
		visited.add(vertex);
		for (Vertex<T> vert : vertex.getAdjacentVertexes()) {
			if (!visited.contains(vert)) {
				topSortUtil(stack, visited, vert);
			}
		}
		stack.add(vertex);
	}

	public static void main(String args[]) {
		Graph<Integer> graph = new Graph<>(true);
		graph.addEdge(1, 3);
		graph.addEdge(1, 2);
		graph.addEdge(3, 4);
		graph.addEdge(5, 6);
		graph.addEdge(6, 3);
		graph.addEdge(3, 8);
		graph.addEdge(8, 11);
		graph.addEdge(5, 3);

		TopologicalSort<Integer> sort = new TopologicalSort<>();
		Stack<Vertex<Integer>> result = sort.topologicalSort(graph);
		while (!result.isEmpty()) {
			System.out.println(result.pop());
		}
	}
}