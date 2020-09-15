package primsAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryMinHeap {

	private List<Node> allNodes = new ArrayList<>();
	private Map<Long, Integer> nodePosition = new HashMap<>();

	public class Node {
		long weight;
		long key;
	}

	public boolean containsData(long id) {
		return nodePosition.containsKey(id);
	}

	public void add(int weight, long l) {
		Node node = new Node();
		node.weight = weight;
		node.key = l;
		allNodes.add(node);
		int size = allNodes.size();
		int current = size - 1;
		int parentIndex = (current - 1) / 2;
		nodePosition.put(node.key, current);

		while (parentIndex >= 0) {
			Node parentNode = allNodes.get(parentIndex);
			Node currentNode = allNodes.get(current);
			if (parentNode.weight > currentNode.weight) {
				swap(parentNode, currentNode);
				updatePositionMap(parentNode.key, currentNode.key, parentIndex, current);
				current = parentIndex;
				parentIndex = (parentIndex - 1) / 2;
			} else {
				break;
			}
		}
	}

	public Long min() {
		return allNodes.get(0).key;
	}

	public boolean empty() {
		return allNodes.size() == 0;
	}

	public void decrease(long data, int newWeight) {
		Integer position = nodePosition.get(data);
		allNodes.get(position).weight = newWeight;
		int parent = (position - 1) / 2;
		while (parent >= 0) {
			if (allNodes.get(parent).weight > allNodes.get(position).weight) {
				swap(allNodes.get(parent), allNodes.get(position));
				updatePositionMap(allNodes.get(parent).key, allNodes.get(position).key, parent, position);
				position = parent;
				parent = (parent - 1) / 2;
			} else {
				break;
			}
		}
	}

	public Long getWeight(long key) {
		Integer position = nodePosition.get(key);
		if (position == null) {
			return null;
		} else {
			return allNodes.get(position).weight;
		}
	}

	public Node extractMinNode() {
		int size = allNodes.size() - 1;
		Node minNode = new Node();
		minNode.key = allNodes.get(0).key;
		minNode.weight = allNodes.get(0).weight;

		long lastNodeWeight = allNodes.get(size).weight;
		allNodes.get(0).weight = lastNodeWeight;
		allNodes.get(0).key = allNodes.get(size).key;
		nodePosition.remove(minNode.key);
		nodePosition.remove(allNodes.get(0));
		nodePosition.put(allNodes.get(0).key, 0);
		allNodes.remove(size);

		int currentIndex = 0;
		size--;
		while (true) {
			int left = 2 * currentIndex + 1;
			int right = 2 * currentIndex + 2;
			if (left > size) {
				break;
			}
			if (right > size) {
				right = left;
			}
			int smallerIndex = allNodes.get(left).weight <= allNodes.get(right).weight ? left : right;
			if (allNodes.get(currentIndex).weight > allNodes.get(smallerIndex).weight) {
				swap(allNodes.get(currentIndex), allNodes.get(smallerIndex));
				updatePositionMap(allNodes.get(currentIndex).key, allNodes.get(smallerIndex).key, currentIndex,
						smallerIndex);
				currentIndex = smallerIndex;
			} else {
				break;
			}
		}
		return minNode;
	}

	public long extractMin() {
		return extractMinNode().key;
	}

	private void swap(Node node1, Node node2) {
		long weight = node1.weight;
		long data = node1.key;

		node1.key = node2.key;
		node1.weight = node2.weight;

		node2.key = data;
		node2.weight = weight;
	}

	private void updatePositionMap(long data1, long data2, int pos1, int pos2) {
		nodePosition.remove(data1);
		nodePosition.remove(data2);
		nodePosition.put(data1, pos1);
		nodePosition.put(data2, pos2);
	}

	public void printHeap() {
		for (Node n : allNodes) {
			System.out.print(n.weight + " ");
		}
	}

}