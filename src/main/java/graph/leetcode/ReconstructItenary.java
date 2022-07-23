package graph.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order.
 * All of the tickets belong to a man who departs from JFK.
 * Thus, the itinerary must begin with JFK.
 * <p>
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 * <p>
 * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 */
public class ReconstructItenary {

    public List<String> findItinerary(List<List<String>> tickets) {
        if (tickets == null || tickets.isEmpty()) return Collections.emptyList();

        Map<String, PriorityQueue<String>> map = new HashMap<>();

        for (List<String> ticket : tickets) {
            map.putIfAbsent(ticket.get(0), new PriorityQueue<>());
            map.get(ticket.get(0)).offer(ticket.get(1));
        }
        List<String> res = new ArrayList<>();
        dfs(map, res, "JFK");
        return res;
    }

    /**
     * Once we construct the adj list
     * JFK : [ATL,SFO]
     * ATL : [JFK,SFO]
     * SFO : [ATL]
     *
     *  in this case we start at JFK we see the adj list, we take the first val 'ALT' and recurse
     *  ATL -> JFK
     *  JFK -> SFO   // since we popped ALT we get SFO
     *  SFO -> ATL
     *  ATL -> SFO
     *
     *  SFO is empty, so we add to stack
     */
    public void dfs(Map<String, PriorityQueue<String>> map, List<String> res, String s) {

        PriorityQueue<String> queue = map.get(s);

        while (!queue.isEmpty()) {
            String temp = queue.poll();
            dfs(map, res, temp);
        }
        res.add(0, s);
    }
}
    
