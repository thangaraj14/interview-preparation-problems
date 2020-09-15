package shortestPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph<T> {

	List<Edge<T>> allEdges = new ArrayList<Edge<T>>();
	HashMap<Integer, Vertex<T>> adjacentList = new HashMap<Integer, Vertex<T>>();

	public void addEdge(int src, int dest, int weight) {
		Vertex vertex1 = null;
		if (adjacentList.containsKey(src)) {
			vertex1 = adjacentList.get(src);
		} else {
			vertex1 = new Vertex<T>(src);
			adjacentList.put(src, vertex1);
		}

		Vertex vertex2 = null;
		if (adjacentList.containsKey(dest)) {
			vertex2 = adjacentList.get(dest);
		} else {
			vertex2 = new Vertex<T>(src);
			adjacentList.put(src, vertex2);
		}

		allEdges.add(new Edge(vertex1, vertex2, weight));
		allEdges.add(new Edge(vertex2, vertex1, weight));
	}

	class Edge<T> {
		Vertex<T> src;
		Vertex<T> dest;
		int weight;

		public Edge(Vertex<T> src, Vertex<T> dest, int weight) {
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}
	}

	class Vertex<T> {
		int id;

		public Vertex(int id) {
			this.id = id;
		}
	}

}
