package graph.bellmanFord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph<T> {

	HashMap<T, Vertex<T>> vertexMap = new HashMap<T, Vertex<T>>();
	List<Edge<T>> allEdges = new ArrayList<Edge<T>>();

	public void addEdges(T u, T v, int weight) {
		Vertex<T> vertex1 = null;
		if (vertexMap.containsKey(u)) {
			vertex1 = vertexMap.get(u);
		} else {
			vertex1 = new Vertex<T>(u);
			vertexMap.put(u, vertex1);
		}

		Vertex<T> vertex2 = null;
		if (vertexMap.containsKey(v)) {
			vertex2 = vertexMap.get(v);
		} else {
			vertex2 = new Vertex<T>(v);
			vertexMap.put(v, vertex2);
		}

		Edge<T> edge = new Edge<T>(vertex1, vertex2, weight);
		allEdges.add(edge);

		vertex1.addAdjacentVertex(vertex2, edge);
	}

}
