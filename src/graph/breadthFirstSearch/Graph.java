package graph.breadthFirstSearch;

import java.util.LinkedList;
import java.util.List;

public class Graph {

	List<Integer> adjacentList[];
	int vertices;

	public Graph(int v) {
		vertices = v;
		adjacentList = new LinkedList[vertices];
		for (int i = 0; i < adjacentList.length; i++) {
			adjacentList[i] = new LinkedList<>();
		}
	}
}
