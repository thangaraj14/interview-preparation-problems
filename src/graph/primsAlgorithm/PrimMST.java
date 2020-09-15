package primsAlgorithm;

import java.util.*;

/**
 *
 * Space complexity - O(E + V) Time complexity - O(ElogV)
 *
 */
public class PrimMST {

	public List<Edge> primMST(Graph graph) {

		BinaryMinHeap minHeap = new BinaryMinHeap();
		Map<Vertex, Edge> vertexToEdge = new HashMap<>();
		List<Edge> result = new ArrayList<>();

		for (Vertex v : graph.getAllVertex()) {
			minHeap.add(Integer.MAX_VALUE, v.getId());
		}

		Vertex startVertex = graph.getAllVertex().iterator().next();
		minHeap.decrease(startVertex.id, 0);

		while (!minHeap.empty()) {
			long currentId = minHeap.extractMin();
			Vertex current = graph.getVertex(currentId);

			Edge spanningTreeEdge = vertexToEdge.get(current);
			
			if (spanningTreeEdge != null) {
				result.add(spanningTreeEdge);
			}

			for (Edge edge : current.getEdges()) {
				Vertex adjacent = getVertexForEdge(current, edge);

				if (minHeap.containsData(adjacent.id) && minHeap.getWeight(adjacent.id) > edge.getWeight()) {
					minHeap.decrease(adjacent.id, edge.getWeight());
					vertexToEdge.put(adjacent, edge);
				}
			}
		}
		return result;
	}

	private Vertex getVertexForEdge(Vertex v, Edge e) {
		return e.getVertex1().equals(v) ? e.getVertex2() : e.getVertex1();
	}

	public static void main(String args[]) {
		Graph graph = new Graph(false);

		graph.addEdge(1, 2, 3);
		graph.addEdge(2, 3, 1);
		graph.addEdge(3, 1, 1);
		graph.addEdge(1, 4, 1);
		graph.addEdge(2, 4, 3);
		graph.addEdge(4, 5, 6);
		graph.addEdge(5, 6, 2);
		graph.addEdge(3, 5, 5);
		graph.addEdge(3, 6, 4);

		PrimMST prims = new PrimMST();
		Collection<Edge> edges = prims.primMST(graph);
		for (Edge edge : edges) {
			System.out.println(edge);
		}
	}

}