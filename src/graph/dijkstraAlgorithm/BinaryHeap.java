package dijkstraAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryHeap {

	List<Node> allNodes = new ArrayList<Node>();
	Map<Long, Integer> positionMap = new HashMap<Long, Integer>();

	class Node {
		long key;
		int weight;

		public Node(long key, int weight) {
			this.key = key;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return this.key + " " + this.weight;
		}

	}

	protected Node extractMin() {
		BinaryHeap.Node node = allNodes.get(0);
		int size = allNodes.size() - 1;
		allNodes.set(0, allNodes.get(size));
		positionMap.remove(node.key);
		positionMap.put(allNodes.get(size).key, 0);
		allNodes.remove(size);

		int currentPosition = 0;
		int left = 1;
		int right = 2;

		if (size > 1) {
			Node currentNode = allNodes.get(0);
			Node leftNode = null;
			Node rightNode = null;

			if (left < size) {
				leftNode = allNodes.get(left);
			}
			if (right < size) {
				rightNode = allNodes.get(right);
			}
			int smallValuePosition = ((rightNode != null) && (rightNode.weight < leftNode.weight)) ? right : left;

			BinaryHeap.Node parentNode = allNodes.get(smallValuePosition);
			swapNodes(currentPosition, currentNode, smallValuePosition, parentNode);
			updatePositionMap(currentPosition, smallValuePosition, currentNode.key, parentNode.key);

			left = 2 * smallValuePosition + 1;
			right = 2 * smallValuePosition + 2;
			currentNode = allNodes.get(smallValuePosition);
		}
		return node;
	}

	protected void addNode(int i, long key) {
		Node node = new Node(key, i);
		allNodes.add(node);
		int currentPosition = allNodes.size() - 1;
		int parentPosition = (currentPosition - 1) / 2;

		positionMap.put(key, currentPosition);

		BinaryHeap.Node parentNode = allNodes.get(parentPosition);
		BinaryHeap.Node currentNode = allNodes.get(currentPosition);
		while (parentNode.weight > currentNode.weight) {
			swapNodes(currentPosition, currentNode, parentPosition, parentNode);
			updatePositionMap(currentPosition, parentPosition, currentNode.key, parentNode.key);

			currentPosition = parentPosition;
			parentPosition = (currentPosition - 1) / 2;

			currentNode = allNodes.get(currentPosition);
			parentNode = allNodes.get(parentPosition);
		}
	}

	private void updatePositionMap(int currentPosition, int parentPosition, long current, long parent) {
		positionMap.put(current, parentPosition);
		positionMap.put(parent, currentPosition);
	}

	private void swapNodes(int currentPosition, BinaryHeap.Node currentNode, int parentPosition,
			BinaryHeap.Node parentNode) {
		allNodes.set(currentPosition, parentNode);
		allNodes.set(parentPosition, currentNode);
	}

	public void decrease(long vertex, int i) {

		Integer position = positionMap.get(vertex);
		allNodes.get(position).weight = i;

		int parent = (position - 1) / 2;

		while (parent >= 0) {
			if (allNodes.get(parent).weight > allNodes.get(position).weight) {
				swapNodes(position, allNodes.get(position), parent, allNodes.get(parent));
				updatePositionMap(position, parent, allNodes.get(parent).key, allNodes.get(position).key);
				position = parent;
				parent = (parent - 1) / 2;
			} else {
				break;
			}
		}

	}

	public boolean isEmpty() {
		return allNodes.size() == 0;
	}

	public boolean containsData(Vertex adjacentVertex) {
		return positionMap.containsKey(adjacentVertex.key);
	}
}
