package geeksforgeeks;

/**
 * Given an array of 4 digits, return the largest 24 hour time that can be made.

The smallest 24 hour time is 00:00, and the largest is 23:59.  Starting from 00:00, a time is larger if more time has elapsed since midnight.

Return the answer as a string of length 5.  If no valid time can be made, return an empty string.
 */
public class LargestTimeFromDigits{
    public String largestTimeFromDigits(int[] A) {
        if(A==null || A.length==0) return "";
        String result="";
        // because A.length == 4
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                for(int k=0;k<4;k++){
                    //We cannot take a number twice. i, j, k and (6-i-j-k) denoting the indices of 4 numbers should be distinct.
                    if(i==j || j==k|| k==i) continue;
                    String hrs= A[i]+""+A[j];
                    //We are trying out all possible ordering, 
                    //indices of 4 numbers are 0, 1, 2, and 3. 
                    //sum of indices = 0 + 1 + 2 + 3= 6
                    //i, j and k denote 3 indices.
                    //So, if we know 3 numbers, 
                    //the 4th number will be the remaining index, i.e., 6-i-j-k
                    
                    String mins= A[k]+""+A[6-i-j-k];
                    
                    if(hrs.compareTo("24")<0 && mins.compareTo("59")<0 && result.compareTo(hrs+":"+mins)<0){
                        result=hrs+":"+mins;
                    }
                        
                }
            }
        }
        return result;
    }
}