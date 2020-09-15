package graph.depthFirstSearch;

import java.util.LinkedList;
import java.util.Stack;

public class DepthFirstSearch {

	public static void main(String[] args) {
		DepthFirstSearch dfs = new DepthFirstSearch();
		Graph graph = new Graph();

		dfs.addEdges('A', 'B', graph);
		dfs.addEdges('C', 'B', graph);
		dfs.addEdges('C', 'D', graph);
		dfs.addEdges('D', 'E', graph);
		dfs.addEdges('A', 'E', graph);
		dfs.addEdges('D', 'B', graph);

		dfs.print(graph, 'B');
	}

	private void addEdges(char src, char dest, Graph graph) {
		checkAndAdd(src, dest, graph);
		checkAndAdd(dest, src, graph);
	}

	private void checkAndAdd(char src, char dest, Graph graph) {
			graph.adjacentMap.getOrDefault(src,new LinkedList<>()).add(dest);

	}

	private void print(Graph graph, Character src) {
		Stack<Character> stack = new Stack<Character>();
		stack.push(src);
		LinkedList<Character> list = new LinkedList<Character>();
		list.add(src);

		while (!stack.isEmpty()) {
			Character pop = stack.pop();
			LinkedList<Character> linkedList = graph.adjacentMap.get(pop);
			if (!linkedList.isEmpty()) {
				Character removeFirst = linkedList.removeFirst();
				stack.push(removeFirst);
				if (!list.contains(removeFirst))
					list.add(removeFirst);
			}
		}

		for (Character ch : list) {
			System.out.println(ch);
		}

	}

}
