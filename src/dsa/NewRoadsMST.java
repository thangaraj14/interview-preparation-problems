package dsa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewRoadsMST {

    public static void main(String[] args) {
        Graph graph = new Graph(false);
        graph.addEdge(1, 4, -1);
        graph.addEdge(2, 3, -1);
        graph.addEdge(4, 5, -1);
        graph.addEdge(1, 2, 5);
        graph.addEdge(1, 3, 10);
        graph.addEdge(1, 6, 2);
        graph.addEdge(5, 6, 5);

        NewRoadsMST newRoads = new NewRoadsMST();
        newRoads.mst(graph);
    }

    private void mst(Graph graph) {
        List<Edge> edges = graph.allEdges;
        EdgeComparator edgeComparator = new EdgeComparator();
        Collections.sort(edges, edgeComparator);
        DisjointSet disjointSet = new DisjointSet();
        List<Edge> resultEdges = new ArrayList<>();
        Collection allVertex = graph.getAllVertex();
        for (Object vertex : allVertex) {
            disjointSet.makeNode((Vertex) vertex);
        }

        for (Edge edge : edges) {
            if (edge.weight < 0) {
                if (disjointSet.unionSet(edge)) {
                    resultEdges.add(edge);
                }
            }
        }

        for (Edge edge : edges) {
            if (edge.weight > 0) {
                if (disjointSet.unionSet(edge)) {
                    resultEdges.add(edge);
                }
            }
        }

        for (Edge edge : resultEdges) {
            System.out.println(edge.vertex1.data + "->" + edge.vertex2.data + "->" + edge.weight);
        }
    }

}

class EdgeComparator implements Comparator<Edge> {

    @Override
    public int compare(Edge e1, Edge e2) {
        if (e1.weight < e2.weight) {
            return -1;
        }
        return 1;
    }
}

class DisjointSet {

    Map<Long, Node> allNodes = new HashMap<>();

    protected void makeNode(Vertex vertex) {
        Node node = new Node(vertex.id);
        node.parent = node;
        allNodes.put(vertex.id, node);
    }

    public boolean unionSet(Edge edge) {
        
        Node srcNode = allNodes.get(edge.vertex1.data);
        Node destNode = allNodes.get(edge.vertex2.data);

        Node parentSrcNode = findParent(srcNode);
        Node parentDestNode = findParent(destNode);

        if (parentSrcNode != parentDestNode) {
            parentDestNode.parent = parentSrcNode;
            return true;
        }
        return false;
    }

    public Node findParent(Node node) {
        Node parent = node.parent;
        if (parent == node) {
            return node;
        }
        return findParent(node.parent);
    }
}

class Node {
    long data;
    Node parent;
    Node child;

    public Node(long id) {
        this.data = id;
    }
}
