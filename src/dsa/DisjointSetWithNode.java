package dsa;

import java.util.HashMap;
import java.util.Map;

public class DisjointSetWithNode {

    Map<Integer, Node> map = new HashMap<>();

    public class Node {
        int rank;
        Node parent;
        int data;
    }

    private void makeSet(int data) {
        Node node = new Node();
        node.data = data;
        node.parent = node;
        map.put(data, node);
    }

    private void unionSet(int src, int dest) {
        Node srcNode = findSet(src);
        Node destNode = findSet(dest);

        if (srcNode.rank >= destNode.rank) {
            srcNode.rank = srcNode.rank + (destNode.rank == 0 ? 1 : destNode.rank);
            destNode.parent = srcNode;
        } else {
            srcNode.parent = destNode;
        }
    }

    private Node findSet(int src) {
        Node node = map.get(src);

        if (node == node.parent) {
            return node;
        }

        return findSet(node.parent.data);
    }

    public static void main(String[] args) {
        DisjointSetWithNode ds = new DisjointSetWithNode();
        ds.makeSet(1);
        ds.makeSet(2);
        ds.makeSet(3);
        ds.makeSet(4);
        ds.makeSet(5);
        ds.makeSet(6);
        ds.makeSet(7);

        ds.unionSet(1, 2);
        ds.unionSet(3, 4);
        ds.unionSet(2, 3);

        System.out.println(ds.findSet(4).data);

    }
}
