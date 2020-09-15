package graph.bellmanFord;

public class Edge<T> {

	Vertex<T> u;
	Vertex<T> v;
	int weight;

	public Edge(Vertex<T> u, Vertex<T> v, int weight) {
		this.u = u;
		this.v = v;
		this.weight = weight;
	}

}
