package geeksforgeeks;

//https://leetcode.com/problems/ipo/
// Input: distinctProjToFind=2, capital=0, Profits=[1,2,3], Capital=[0,1,1].

// Output: 4

// Explanation: Since your initial capital is 0, you can only start the project indexed 0.
//              After finishing it you will obtain profit 1 and your capital becomes 1.
//              With capital 1, you can either start the project indexed 1 or the project indexed 2.
//              Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
//              Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
public class IPOMaxProfit {

    // Create (capital, profit) pairs and put them into PriorityQueue pqCap.
    // This PriorityQueue sort by capital increasingly.
    // Keep polling pairs from pqCap until the project out of current capital capability. Put them into
    // PriorityQueue pqPro which sort by profit decreasingly.
    // Poll one from pqPro, it's guaranteed to be the project with max profit and within current capital capability. 
    //Add the profit to capital W.
    //Repeat step 2 and 3 till finish k steps or no suitable project (pqPro.isEmpty()).
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        
        PriorityQueue<int[]> minQueue= new PriorityQueue<>((a,b)->Integer.compare(a[0],b[0]));
        PriorityQueue<int[]> maxQueue= new PriorityQueue<>((a,b)->Integer.compare(b[1],a[1]));
        
        for(int i=0;i<Profits.length;i++){
            minQueue.offer(new int[]{Capital[i], Profits[i]});
        }
        for (int i = 0; i < k; i++) {
            while(!minQueue.isEmpty() && minQueue.peek()[0]<=W){
                maxQueue.offer(minQueue.poll());
            }
            
            if(maxQueue.isEmpty()) break;
            
            W+=maxQueue.poll()[1];
           
        }
        return W;
        
    }
}