package dijkstraAlgorithm;

import java.util.HashMap;
import java.util.List;

public class DijkstraAlgorithm {

	public static <T> void main(String[] args) {
		Graph graph = new Graph();
		graph.addEdge(1, 2, 5);
		graph.addEdge(1, 5, 2);
		graph.addEdge(1, 4, 9);
		graph.addEdge(2, 3, 2);
		graph.addEdge(3, 4, 3);
		graph.addEdge(4, 6, 2);
		graph.addEdge(5, 6, 3);

		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm();
		dijkstra.singleSourceShortestPath(graph);
	}

	private void singleSourceShortestPath(Graph graph) {
		BinaryHeap heap = new BinaryHeap();
		HashMap<Vertex, Integer> distanceMap = new HashMap<Vertex, Integer>();
		HashMap<Vertex, Vertex> parentMap = new HashMap<Vertex, Vertex>();

		Vertex vertex = graph.vertexMap.get((long) 1);

		for (Vertex ver : graph.vertexMap.values()) {
			heap.addNode(Integer.MAX_VALUE, ver.key);
		}
		heap.decrease(vertex.key, 0);
		distanceMap.put(vertex, 0);
		while (!heap.isEmpty()) {
			long currentKey = heap.extractMin().key;
			Vertex currentVertex = graph.vertexMap.get(currentKey);

			System.out.println(currentVertex.key);
			List<Edge> allEdges = currentVertex.allEdges;

			for (Edge edge : allEdges) {
				Vertex adjacentVertex = getAdjacentVertex(currentVertex, edge);
				if (heap.containsData(adjacentVertex)) {
					int weight = 0;
					if (distanceMap.containsKey(adjacentVertex)) {
						weight = distanceMap.get(adjacentVertex);
					}
					weight = edge.weight + weight;
					if (heap.allNodes.get(heap.positionMap.get(adjacentVertex.key)).weight > weight) {
						heap.decrease(adjacentVertex.key, weight);
						parentMap.put(adjacentVertex, currentVertex);
						distanceMap.put(adjacentVertex, weight);
					}
				}
			}
		}
	}

	private Vertex getAdjacentVertex(Vertex currentVertex, Edge edge) {
		return edge.vertex1.key == currentVertex.key ? edge.vertex2 : edge.vertex1;

	}

}
