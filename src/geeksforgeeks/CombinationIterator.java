package geeksforgeeks;

// CombinationIterator iterator = new CombinationIterator("abc", 2); // creates the iterator.

import java.util.ArrayDeque;
import java.util.Deque;

// iterator.next(); // returns "ab"
// iterator.hasNext(); // returns true
// iterator.next(); // returns "ac"
// iterator.hasNext(); // returns true
// iterator.next(); // returns "bc"
// iterator.hasNext(); // returns false
public class CombinationIterator {
    Deque<String> deque;
    boolean[] visited;
    public CombinationIterator(String characters, int combinationLength) {
        deque= new ArrayDeque<>();
        visited= new boolean[characters.length()];
        generateCombinations(characters,deque, new StringBuilder(), combinationLength, 0, visited);
    }
    
    public String next() {
        if(hasNext()) return deque.poll();
        
        return "";
    }
    
    public boolean hasNext() {
        return !deque.isEmpty();
    }
    
    public void generateCombinations(String characters, Deque<String> deque,StringBuilder sb, int limit, int start, boolean[] visited){
        
        if(sb.length()==limit){
            deque.offer(new String(sb.toString()));
            return;
        }
        
        for(int i=start; i<characters.length();i++){
            if(visited[i]) continue;
            sb.append(""+characters.charAt(i));
            generateCombinations(characters,deque, sb, limit, i+1, visited);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}