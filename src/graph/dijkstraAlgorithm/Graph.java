package graph.dijkstraAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

	Map<Long, Vertex> vertexMap = new HashMap<Long, Vertex>();
	List<Edge> allEdges = new ArrayList<Edge>();

	public void addEdge(long v1, long v2, int weight) {
		Vertex vertex1 = null;
		if (vertexMap.containsKey(v1)) {
			vertex1 = vertexMap.get(v1);
		} else {
			vertex1 = new Vertex(v1);
			vertexMap.put(v1, vertex1);
		}

		Vertex vertex2 = null;
		if (vertexMap.containsKey(v2)) {
			vertex2 = vertexMap.get(v2);
		} else {
			vertex2 = new Vertex(v2);
			vertexMap.put(v2, vertex2);
		}

		Edge edge = new Edge(vertex1, vertex2, weight);
		allEdges.add(edge);
		vertex1.adjacentVertexAndEdge(vertex2, edge);
		vertex2.adjacentVertexAndEdge(vertex1, edge);
	}
}

class Vertex {
	long key;
	List<Vertex> adjacentVertex = new ArrayList<Vertex>();
	List<Edge> allEdges = new ArrayList<Edge>();

	public Vertex(long key) {
		this.key = key;
	}

	public void adjacentVertexAndEdge(Vertex vertex, Edge edge) {
		allEdges.add(edge);
		adjacentVertex.add(vertex);
	}

	@Override
	public String toString() {
		return this.key + "";
	}
}

class Edge {
	Vertex vertex1;
	Vertex vertex2;
	int weight;

	public Edge(Vertex vertex1, Vertex vertex2, int weight) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return this.vertex1 + "-->" + this.vertex2;
	}
}