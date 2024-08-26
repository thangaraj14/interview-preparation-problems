package practiceproblems;

import java.util.ArrayList;

/**
 * https://www.geeksforgeeks.org/find-the-row-with-maximum-number-1s/
 */
public class MatrixRowWithMax1 {

    public int[] rowAndMaximumOnes(int[][] mat) {

        var result = new ArrayList<Integer>();
        int maxSoFar=-1;
        int m = mat.length;
        int n = mat[0].length;

        for(int i=0;i<m;i++){
            int onesCount = n-lowerBound(mat[i],1);
            if(onesCount>maxSoFar){
                maxSoFar = onesCount;
                result = new ArrayList<Integer>();
                result.add(i);
            }else if(onesCount==maxSoFar){
                result.add(i);
            }

        }

        return result.stream().mapToInt(i->i).toArray();

    }

    public int lowerBound(int[] arr, int x){
        int left=0;
        int right=arr.length-1;

        while(left<right){
            int mid = (left+right)/2;

            if(arr[mid]==x){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return left;
    }

}
