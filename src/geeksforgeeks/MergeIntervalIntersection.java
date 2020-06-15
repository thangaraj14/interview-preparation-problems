package geeksforgeeks;

/**
 * 
Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
Return the intersection of these two interval lists.

Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 */
public class MergeIntervalIntersection {

    // inorder to find a overlapping part alone between two intervals
    // we take  
    //  start = max(a.start, b.start)
    // end = min(a.end, b.end) 
    // That is, the highest start time and the lowest end time will be the overlapping interval.
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int i=0;
        int j=0;
        List<int[]> result= new ArrayList<>();
        while(i<A.length && j<B.length){

            if(A[i][0]>=B[j][0] && A[i][0]<=B[j][1] ||
               B[j][0]>=A[i][0] && B[j][0]<=A[i][1]){ // this condition checks if there'a ovelapping
               //A=>[0,2], B=> [1,5]
                result.add(new int[]{Math.max(A[i][0],B[j][0]), Math.min(A[i][1],B[j][1])});
            }
            // once added to result move the i or j based on lesser end time
            if(A[i][1]<B[j][1]){
                i++;
            }else{
                j++;
            }
        }
        
        return result.toArray(new int[result.size()][2]);
    }
}