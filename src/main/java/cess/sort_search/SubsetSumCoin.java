package cess.sort_search;

import java.util.Arrays;

public class SubsetSumCoin {

    public static int sumCannotFormedBySubset(int[] arr) {
        int res = 1; // Initialize result

        // sort the input array
        Arrays.sort(arr);

        // Traverse the array and increment 'res' if arr[i] is
        // smaller than or equal to 'res'.
        for (int i = 0; i < arr.length; i++)
        {
            if(arr[i] > res){
                return res;
            }
            else{
                res+=arr[i];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(sumCannotFormedBySubset(new int[]{2,9,1,2,7}));
    }
}
