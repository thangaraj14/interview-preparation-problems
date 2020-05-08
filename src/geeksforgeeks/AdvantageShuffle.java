package geeksforgeeks;

// the advantage of A with respect to B is the number of indices i for which A[i] > B[i].

// Return any permutation of A that maximizes its advantage with respect to B.
//Input: A = [12,24,8,32], B = [13,25,32,11]
//Output: [24,32,8,12]
public class AdvantageShuffle {
    public int[] advantageCount(int[] A, int[] B) {
        Arrays.sort(A); 
        
        PriorityQueue<Integer[]> pq= new PriorityQueue<>((a,b)->Integer.compare(b[0],a[0]));
        for(int i=0;i<B.length;i++){
            pq.offer(new Integer[]{B[i],i}); // add elements of B along with it's index to max queue
        }
        int[] result= new int[A.length]; // new placeholder for result;
        int lo=0; int hi= A.length-1; // start and end index
        while(!pq.isEmpty()){
            Integer[] temp= pq.poll();
            int index=temp[1];
            int val=temp[0];
            
            if(A[hi]>val){ // if polled element is lesser thar A[hi], put A[hi] at index of
                            // queued elements index, means, equal to B's current index we are putting 
                            // a value greater that B's in result arrays
                result[index]=A[hi--];
            }else{
                result[index]=A[lo++];
            }
        }
        return result;
    }
}