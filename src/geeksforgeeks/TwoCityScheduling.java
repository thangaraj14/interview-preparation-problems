package geeksforgeeks;

/**
 * There are 2N people a company is planning to interview. 
 * The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].
 * Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.
 * Input: [[10,20],[30,200],[400,50],[30,20]]
 * Output: 110
 * Explanation: 
   The first person goes to city A for a cost of 10.
   The second person goes to city A for a cost of 30.
   The third person goes to city B for a cost of 50.
   The fourth person goes to city B for a cost of 20.

 * The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city
 * 
 */
public class TwoCityScheduling {
    public int twoCitySchedCost(int[][] costs) {
        // for input [[10,20],[30,200],[400,50],[30,20]]
        // let's send all to A, the cost'd be => 10+30+400+30= 470
        // we need to remove one half to save some money
        // what we can do is take the difference in each input
        //  (10-20) (30-200)  (400-50)  (30-20)
        // [-10,    -170,     350,       10]
        // what the above array indicates is that for i'th candidate=>[10,20] we will save 10 in sending to A
        // when i=2 we will have to spend 350 more to bring to A city so we sort by arr[0]-arr[1]
        // greedily we take negative val candidates to A and rest to B for this input 
        PriorityQueue<int[]> queue= new PriorityQueue<>((a,b)->(a[0] - a[1]) - (b[0] - b[1]));
        
        for(int[] cost: costs){
            queue.offer(cost);
        }
        int result=0;
        int k=0;
        while(k<costs.length/2){
            //System.out.println(Arrays.toString(queue.peek()));
            result+=queue.poll()[0];
            k++;
        }
        while(!queue.isEmpty()){
              //System.out.println(Arrays.toString(queue.peek()));
            result+=queue.poll()[1];
        }
        return result;
    }
}