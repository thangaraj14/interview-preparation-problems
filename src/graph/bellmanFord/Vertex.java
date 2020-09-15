package graph.bellmanFord;

import java.util.ArrayList;
import java.util.List;

class Vertex<T> {
	T key;
	List<Vertex<T>> adjacentVertex = new ArrayList<Vertex<T>>();
	List<Edge<T>> edges = new ArrayList<Edge<T>>();

	public Vertex(T key) {
		this.key = key;
	}

	public void addAdjacentVertex(Vertex<T> vertex2, Edge<T> edge) {
		adjacentVertex.add(vertex2);
		edges.add(edge);
	}

}