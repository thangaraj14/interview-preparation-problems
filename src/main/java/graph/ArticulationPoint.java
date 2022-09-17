package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Date 08/22/2015
 * 
 * @author Tushar Roy
 *
 *         Find articulation points in connected undirected graph. Articulation
 *         points are vertices such that removing any one of them disconnects
 *         the graph.
 *
 *         We need to do DFS of this graph and keep visitedTime and lowTime for
 *         each vertex. lowTime is keeps track of back edges.
 *
 *         If any one of following condition meets then vertex is articulation
 *         point.
 *
 *         1) If vertex is root of DFS and has atlesat 2 independent
 *         children.(By independent it means they are not connected to each
 *         other except via this vertex). This condition is needed because if we
 *         started from corner vertex it will meet condition 2 but still is not
 *         an articulation point. To filter out those vertices we need this
 *         condition.
 *
 *         2) It is not root of DFS and if visitedTime of vertex <= lowTime of
 *         any adjacent vertex then its articulation point.
 *
 *         Time complexity is O(E + V) Space complexity is O(V)
 *
 *         References: https://en.wikipedia.org/wiki/Biconnected_component
 *         http://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
 */
public class ArticulationPoint<T> {

	private int time;

	public Set<Vertex<T>> findarticulationPoints(Graph<T> graph) {
		time = 0;
		Set<Vertex<T>> visited = new HashSet<>();
		Set<Vertex<T>> articulationPoints = new HashSet<>();
		Vertex<T> startVertex = graph.getAllVertex().iterator().next();

		Map<Vertex<T>, Integer> visitedTime = new HashMap<>();
		Map<Vertex<T>, Integer> lowTime = new HashMap<>();
		Map<Vertex<T>, Vertex<T>> parent = new HashMap<>();

		DFS(visited, articulationPoints, startVertex, visitedTime, lowTime, parent);
		return articulationPoints;
	}

	private void DFS(Set<Vertex<T>> visited, Set<Vertex<T>> articulationPoints, Vertex<T> vertex,
			Map<Vertex<T>, Integer> visitedTime, Map<Vertex<T>, Integer> lowTime, Map<Vertex<T>, Vertex<T>> parent) {
		visited.add(vertex);
		visitedTime.put(vertex, time);
		lowTime.put(vertex, time);
		time++;
		int childCount = 0;
		boolean isArticulationPoint = false;
		for (Vertex<T> adj : vertex.getAdjacentVertexes()) {
			// if adj is same as parent then just ignore this vertex.
			if (adj.equals(parent.get(vertex))) {
				continue;
			}
			// if adj has not been visited then visit it.
			if (!visited.contains(adj)) {
				parent.put(adj, vertex);
				childCount++;
				DFS(visited, articulationPoints, adj, visitedTime, lowTime, parent);

				if (visitedTime.get(vertex) <= lowTime.get(adj)) {
					isArticulationPoint = true;
				} else {
					// below operation basically does lowTime[vertex] = min(lowTime[vertex],
					// lowTime[adj]);
					lowTime.compute(vertex, (currentVertex, time) -> Math.min(time, lowTime.get(adj)));
				}

			} else { // if adj is already visited see if you can get better low time.
				// below operation basically does lowTime[vertex] = min(lowTime[vertex],
				// visitedTime[adj]);
				lowTime.compute(vertex, (currentVertex, time) -> Math.min(time, visitedTime.get(adj)));
			}
		}

		// checks if either condition 1 or condition 2 meets). If yes then it is
		// articulation point.
		if ((parent.get(vertex) == null && childCount >= 2) || parent.get(vertex) != null && isArticulationPoint) {
			articulationPoints.add(vertex);
		}

	}

	public static void main(String args[]) {
		Graph<Integer> graph = new Graph<>(false);
	/*	graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(4, 5);
		graph.addEdge(5, 6);
		graph.addEdge(6, 7);
		graph.addEdge(7, 5);
		graph.addEdge(6, 8);*/

		graph.addEdge(4, 3);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);


		// bigger example
		/*
		 * graph.addEdge(0, 1); graph.addEdge(0, 2); graph.addEdge(0, 3);
		 * graph.addEdge(0, 4); graph.addEdge(4, 2); graph.addEdge(3, 5);
		 * graph.addEdge(4, 6); graph.addEdge(6, 3); graph.addEdge(6, 7);
		 * graph.addEdge(6, 8); graph.addEdge(7, 9); graph.addEdge(9, 10);
		 * graph.addEdge(8, 10);
		 */

		ArticulationPoint<Integer> ap = new ArticulationPoint<>();
		Set<Vertex<Integer>> aPoints = ap.findarticulationPoints(graph);
		aPoints.forEach(System.out::println);
	}

}

class Graph<T> {

	List<Edge<T>> allEdges;
	Map<Long, Vertex<T>> allVertex;
	boolean isDirected = false;

	public Graph(boolean isDirected) {
		allEdges = new ArrayList<>();
		allVertex = new HashMap<>();
		this.isDirected = isDirected;
	}

	public void addEdge(long id1, long id2) {
		addEdge(id1, id2, 0);
	}

	public void addVertex(Vertex<T> vertex) {
		if (allVertex.containsKey(vertex.getId())) {
			return;
		}
		allVertex.put(vertex.getId(), vertex);
		for (Edge<T> edge : vertex.getEdges()) {
			allEdges.add(edge);
		}
	}

	public Vertex<T> addSingleVertex(long id) {
		if (allVertex.containsKey(id)) {
			return allVertex.get(id);
		}
		Vertex<T> v = new Vertex<>(id);
		allVertex.put(id, v);
		return v;
	}

	public Vertex<T> getVertex(long id) {
		return allVertex.get(id);
	}

	public void addEdge(long id1, long id2, int weight) {
		Vertex<T> vertex1 = null;
		if (allVertex.containsKey(id1)) {
			vertex1 = allVertex.get(id1);
		} else {
			vertex1 = new Vertex<>(id1);
			allVertex.put(id1, vertex1);
		}
		Vertex<T> vertex2 = null;
		if (allVertex.containsKey(id2)) {
			vertex2 = allVertex.get(id2);
		} else {
			vertex2 = new Vertex<>(id2);
			allVertex.put(id2, vertex2);
		}

		Edge<T> edge = new Edge<>(vertex1, vertex2, isDirected, weight);
		allEdges.add(edge);
		vertex1.addAdjacentVertex(edge, vertex2);
		if (!isDirected) {
			vertex2.addAdjacentVertex(edge, vertex1);
		}
	}

	public List<Edge<T>> getAllEdges() {
		return allEdges;
	}

	public Collection<Vertex<T>> getAllVertex() {
		return allVertex.values();
	}

	public void setDataForVertex(long id, T data) {
		if (allVertex.containsKey(id)) {
			Vertex<T> vertex = allVertex.get(id);
			vertex.setData(data);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Edge<T> edge : getAllEdges()) {
			sb.append(edge.getVertex1() + " " + edge.getVertex2() + " " + edge.getWeight());
			sb.append("\n");
		}
		return sb.toString();
	}
}

class Vertex<T> {
	long id;
	T data;
	List<Edge<T>> edges = new ArrayList<>();
	List<Vertex<T>> adjacentVertex = new ArrayList<>();

	Vertex(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void addAdjacentVertex(Edge<T> e, Vertex<T> v) {
		edges.add(e);
		adjacentVertex.add(v);
	}

	public String toString() {
		return String.valueOf(id);
	}

	public List<Vertex<T>> getAdjacentVertexes() {
		return adjacentVertex;
	}

	public List<Edge<T>> getEdges() {
		return edges;
	}

	public int getDegree() {
		return edges.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex<?> other = (Vertex<?>) obj;

		return id != other.id;
	}
}

class Edge<T> {
	boolean isDirected = false;
	Vertex<T> vertex1;
	Vertex<T> vertex2;
	int weight;

	Edge(Vertex<T> vertex1, Vertex<T> vertex2) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
	}

	Edge(Vertex<T> vertex1, Vertex<T> vertex2, boolean isDirected, int weight) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.weight = weight;
		this.isDirected = isDirected;
	}

	Edge(Vertex<T> vertex1, Vertex<T> vertex2, boolean isDirected) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.isDirected = isDirected;
	}

	Vertex<T> getVertex1() {
		return vertex1;
	}

	Vertex<T> getVertex2() {
		return vertex2;
	}

	int getWeight() {
		return weight;
	}

	public boolean isDirected() {
		return isDirected;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vertex1 == null) ? 0 : vertex1.hashCode());
		result = prime * result + ((vertex2 == null) ? 0 : vertex2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge<?> other = (Edge<?>) obj;
		if (vertex1 == null) {
			if (other.vertex1 != null) {
				return false;
			}
		} else if (!vertex1.equals(other.vertex1)) {
			return false;
		}
		if (vertex2 == null) {
			if (other.vertex2 != null) {
				return false;
			}
		} else if (!vertex2.equals(other.vertex2)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Edge [isDirected=" + isDirected + ", vertex1=" + vertex1 + ", vertex2=" + vertex2 + ", weight=" + weight
				+ "]";
	}
}