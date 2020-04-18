package geeksforgeeks;

public class SortedSquares {
    // Input: [-7,-3,2,3,11]
    // Output: [4,9,9,49,121]
    public int[] sortedSquares(int[] A) {
        if(A==null || A.length==0) return new int[0];
        
        int[]result= new int[A.length];
        
        int index= A.length-1;
        int left=0;
        int right=index;
        
        while(left<=right){
            if(Math.abs(A[left])<Math.abs(A[right])){
                result[index]= A[right]*A[right];
                right--;
            }else{
                result[index]=A[left]*A[left];
                left++;
            }
            index--;
        }
        
        return result;
    }
}