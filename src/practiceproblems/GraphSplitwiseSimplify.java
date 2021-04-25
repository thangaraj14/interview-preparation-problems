package practiceproblems;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

/**
 * Design an algorithm to simplify cash flow between people Minimize Cash Flow
 * among a given set of friends who have borrowed money from each other. Given a
 * number of friends who have to give or take some amount of money from one
 * another. Design an algorithm by which the total cash flow among all the
 * friends is minimized. O(VE), V is number of persons and E is number of
 * transactions
 *
 * @author Utkarsh
 * <p>
 * https://github.com/UtkarshBehre/InterviewQuestionsPracticeInJava/blob/master/src/popularQuestionSet/GraphSplitwiseSimplify.java
 */
public class GraphSplitwiseSimplify {

    static class Graph {
        int V;
        LinkedList<AdjNode>[] adjNodesList;

        static class AdjNode {
            int adjVertices;
            int debt;

            public AdjNode(int adjVertices, int debt) {
                this.adjVertices = adjVertices;
                this.debt = debt;
            }

            @Override
            public boolean equals(Object o) {
                if (this.adjVertices == ((AdjNode) o).adjVertices) {
                    return true;
                } else {
                    return false;
                }
            }

            public String toString() {
                return adjVertices + "->" + debt;
            }
        }

        public Graph(int v) {
            this.V = v;
            adjNodesList = new LinkedList[V];
            for (int i = 0; i < V; i++) {
                adjNodesList[i] = new LinkedList<AdjNode>();
            }
        }

        public void addEdge(int src, int dest, int debt) {
            AdjNode adjNode = new AdjNode(dest, debt);
            adjNodesList[src].add(adjNode);
        }

        /**
         * Runtime: O(VE)
         */
        public void simplifyDebts() {
            HashMap<Integer, Integer> takers = new HashMap<>();
            HashMap<Integer, Integer> givers = new HashMap<>();
            int[] debts = new int[V];
            for (int i = 0; i < V; i++) {
                for (AdjNode adjNode : adjNodesList[i]) {
                    debts[i] -= adjNode.debt;
                    debts[adjNode.adjVertices] += adjNode.debt;
                }
            }
            for (int i = 0; i < V; i++) {
                if (debts[i] < 0) {
                    givers.put(i, Math.abs(debts[i]));
                } else if (debts[i] > 0) {
                    takers.put(i, debts[i]);
                }
            }

            adjNodesList = new LinkedList[V];
            for (int i = 0; i < V; i++) {
                adjNodesList[i] = new LinkedList<>();
            }
            for (Entry<Integer, Integer> giver : givers.entrySet()) {
                int amountToGive = giver.getValue();
                for (Entry<Integer, Integer> taker : takers.entrySet()) {
                    int amountToTake = taker.getValue();
                    if (amountToTake > 0) {
                        if (amountToTake < amountToGive) {
                            taker.setValue(0);
                            amountToGive -= amountToTake;
                            addEdge(giver.getKey(), taker.getKey(), amountToTake);
                        } else {
                            taker.setValue(amountToTake - amountToGive);
                            addEdge(giver.getKey(), taker.getKey(), amountToGive);
                            break;
                        }
                    }
                }
                giver.setValue(amountToGive);
            }
        }

        public void printDebts() {
            System.out.println("depts are: ");
            for (int i = 0; i < V; i++) {
                for (AdjNode adjNode : adjNodesList[i]) {
                    System.out.println(i + " owes " + adjNode.adjVertices + " " + adjNode.debt + " bucks.");
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(3);
        g.addEdge(0, 1, 1000);
        g.addEdge(1, 2, 1000);
        g.addEdge(0, 2, 2000);
        g.printDebts();
        g.simplifyDebts();
        System.out.print("After simplification, ");
        g.printDebts();

        System.out.println("\nTest set 2");
        g = new Graph(5);
        g.addEdge(0, 1, 1000);
        g.addEdge(0, 2, 5000);
        g.addEdge(1, 3, 2000);
        g.addEdge(1, 4, 1500);
        g.addEdge(2, 1, 3000);
        g.addEdge(2, 3, 4000);
        g.addEdge(3, 0, 500);
        g.addEdge(4, 3, 500);
        g.printDebts();
        g.simplifyDebts();
        System.out.print("After simplification, ");
        g.printDebts();
    }
}