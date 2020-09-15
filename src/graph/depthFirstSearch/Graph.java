package depthFirstSearch;

import java.util.HashMap;
import java.util.LinkedList;

public class Graph {

	HashMap<Character, LinkedList<Character>> adjacentMap;

	public Graph() {
		adjacentMap = new HashMap<Character, LinkedList<Character>>();
	}

}
