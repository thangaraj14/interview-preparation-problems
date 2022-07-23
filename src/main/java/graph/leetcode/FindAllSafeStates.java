package graph.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-eventual-safe-states
 *
 * similar to Topological sort, asks to remove cycles and return non cyclic paths
 */
public class FindAllSafeStates {

    enum State {
        VISITED,
        VISITING
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> safeNodes = new ArrayList<>(graph.length);
        State[] states = new State[graph.length];
        for (int node = 0; node < graph.length; node++) {
            if (isSafe(graph, node, states)) {
                safeNodes.add(node);
            }
        }
        return safeNodes;
    }

    private boolean isSafe(int[][] graph, int node, State[] states) {
        if (states[node] != null) {
            return states[node] == State.VISITED;
        }

        states[node] = State.VISITING;

        for (int next : graph[node]) {
            if (!isSafe(graph, next, states)) return false;
        }

        states[node] = State.VISITED;
        return true;
    }
}
