package geeksforgeeks;

import java.util.*;

public class JumpsToReachEnd {
    public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
        int furthestReachSoFar = 0, lastlndex = maxAdvanceSteps.size() - 1;
        
        for (int i = 0; i <= furthestReachSoFar && furthestReachSoFar < lastlndex; ++i) {
            // for every index store maxsteps it can take and i should be
            // less than maxSteps to check if it can move further 
            // e.x (3, 2, 0, 0, 2, 0,1) when index i is 3 maxStpes is also 3, it cannot move further
            furthestReachSoFar = Math.max(furthestReachSoFar, i + maxAdvanceSteps.get(i));
        }
        return furthestReachSoFar >= lastlndex;
    }

    public static void main(String[] args) {
       
        List<Integer> list= Arrays.asList(new Integer[]{3,3,1,0, 2,0,1});
        System.out.println(canReachEnd(list));
    }
}