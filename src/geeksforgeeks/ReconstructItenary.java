package geeksforgeeks;
/**
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. 
 * All of the tickets belong to a man who departs from JFK. 
 * Thus, the itinerary must begin with JFK.
 * 
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
   Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]

   Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
   Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 */
public class ReconstructItenary {
   
    class Solution {
        public List<String> findItinerary(List<List<String>> tickets) {
           if(tickets==null || tickets.size()==0) return Collections.emptyList();
   
           Map<String, PriorityQueue<String> > map= new HashMap<>();
   
           for(List<String> ticket: tickets){
               map.putIfAbsent(ticket.get(0), new PriorityQueue<>());
              map.get(ticket.get(0)).offer(ticket.get(1));
           }
           List<String> res = new ArrayList();
           dfs(map, res, "JFK");
           return res;
       }
   
       public void dfs( Map<String, PriorityQueue<String>> map, List<String> res, String s){
   
           PriorityQueue<String> queue= map.get(s);
   
           while(queue!=null && !queue.isEmpty()){
               String temp= queue.poll();
               dfs(map, res, temp);
           }
   
           res.add(0,s);
       }
   }
    
}