package graph.bellmanFord;

import java.util.HashMap;
import java.util.Map;

public class BellmanFord {

	private static int INFINITY = 10000000;

	public static void main(String[] args) {
		Graph<Integer> graph = new Graph<Integer>();
		graph.addEdges(0, 1, 4);
		graph.addEdges(0, 2, 5);
		graph.addEdges(0, 3, 8);
		graph.addEdges(1, 2, -3);
		graph.addEdges(2, 4, 4);
		graph.addEdges(3, 4, 2);
		graph.addEdges(4, 3, 1);

		BellmanFord bf = new BellmanFord();
		Vertex<Integer> startVertex = graph.vertexMap.values().iterator().next();
		bf.shortestPath(graph, startVertex);
	}

	private void shortestPath(Graph<Integer> graph, Vertex<Integer> startVertex) {
		Map<Vertex<Integer>, Integer> distance = new HashMap<>();
		Map<Vertex<Integer>, Vertex<Integer>> parent = new HashMap<>();

		for (Vertex<Integer> vertex : graph.vertexMap.values()) {
			distance.put(vertex, BellmanFord.INFINITY);
			parent.put(vertex, null);
		}
		distance.put(startVertex, 0);

		for (int i = 0; i < graph.vertexMap.size() - 1; i++) {
			for (Edge<Integer> edge : graph.allEdges) {
				int j = distance.get(edge.u) + edge.weight;
				if (j < distance.get(edge.v)) {
					distance.put(edge.v, j);
					parent.put(edge.v, edge.u);
				}
			}
		}

		for (Edge<Integer> edge : graph.allEdges) {
			int j = distance.get(edge.u) + edge.weight;
			if (j < distance.get(edge.v)) {
				throw new NegativeException();
			}
		}
		for (Map.Entry<Vertex<Integer>, Integer> map : distance.entrySet()) {
			System.out.println(map.getKey().key + "-->" + map.getValue());
		}
	}

}
