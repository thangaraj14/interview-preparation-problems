package cess.sort_search;

import java.util.Map;
import java.util.TreeMap;

/**
 * https://cses.fi/problemset/task/1619
 * https://www.youtube.com/watch?v=O9Sptr-RdRo
 */
public class RestaurantCustomers {

    public int maxCustomersAtGivenTime(int[][] arrivalTimes){
        Map<Integer,Integer> cache = new TreeMap<>();

        for (int[] arr:arrivalTimes) {
            cache.put(arr[0],1);
            cache.put(arr[1],-1);
        }
        int ans =0;
        int count =0;
        for (Map.Entry<Integer,Integer> entry : cache.entrySet()){

            ans = Math.max(ans,count+entry.getValue());
        }

        return ans;

    }
}
