package kruskalAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalMST {

	public List<Edge<Integer>> getMST(Graph<Integer> graph) {
		List<Edge<Integer>> allEdges = graph.getAllEdges();

		Collections.sort(allEdges, (edge1, edge2) -> edge1.getWeight() <= edge2.getWeight() ? -1 : 1);
		DisjointSet disjointSet = new DisjointSet();

		for (Vertex<Integer> vertex : graph.getAllVertex()) {
			disjointSet.makeSet(vertex.getId());
		}

		List<Edge<Integer>> resultEdge = new ArrayList<Edge<Integer>>();
		for (Edge<Integer> edge : allEdges) {
			long root1 = disjointSet.findSet(edge.getVertex1().getId());
			long root2 = disjointSet.findSet(edge.getVertex2().getId());

			if (root1 != root2) {
				resultEdge.add(edge);
				disjointSet.union(edge.getVertex1().getId(), edge.getVertex2().getId());
			}
		}
		return resultEdge;
	}

	public static void main(String args[]) {
		Graph<Integer> graph = new Graph<Integer>(false);
		graph.addEdge(1, 2, 4);
		graph.addEdge(1, 3, 1);
		graph.addEdge(2, 5, 1);
		graph.addEdge(2, 6, 3);
		graph.addEdge(2, 4, 2);
		graph.addEdge(6, 5, 2);
		graph.addEdge(6, 4, 3);
		graph.addEdge(4, 7, 2);
		graph.addEdge(3, 4, 5);
		graph.addEdge(3, 7, 8);

		KruskalMST mst = new KruskalMST();
		List<Edge<Integer>> result = mst.getMST(graph);
		int count = 0;
		for (Edge<Integer> edge : result) {
			count += edge.getWeight();
			System.out.println(edge.getVertex1() + " " + edge.getVertex2() + "-> " + count);
		}
	}
}