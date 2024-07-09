package binarysearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * https://leetcode.com/problems/count-number-of-rectangles-containing-each-point
 * the constraints are
 * 1 <= rectangles.length, points.length <= 5 * 10^4
 * 1 <= li, xj <= 10^9
 * 1 <= hi, yj <= 100
 * This tells us that we cannot go through all the rectangles for all the points,
 * coz that will be 5 * 10^4 * 5* 10^4 which would be > 10^8 operations, so would give TLE.
 */
public class NumberOfRectangles {
    //We see that heights are only from 0 to 100.
    // So can traverse in them. But lengths can be till 10^9 so have to do binary search in that.
    public int[] countRectangles(int[][] rectangles, int[][] points) {
        int[] res = new int[points.length];
        List<List<Integer>> group = new ArrayList<>(101);
        for(int i = 0; i < 101; i++){
            group.add(new ArrayList<>());
        }

        for(int[] rec : rectangles){
            int l = rec[0];
            int h = rec[1];
            group.get(h).add(l);
        }

        for(int i = 0; i < 101; i++){
            Collections.sort(group.get(i));
        }

        for(int i = 0; i < points.length; i++){
            int count = 0;
            int x = points[i][0];
            int y = points[i][1];
            for(int j = y; j < 101; j++){
                List<Integer> cur = group.get(j);
                int index = binarySearch(cur, x);
                count += cur.size() - index;
            }
            res[i] = count;
        }

        return res;
    }

    private int binarySearch(List<Integer> list, int x){
        int left = 0;
        int right = list.size();
        while(left < right){
            int mid = left + (right - left) / 2;
            if(list.get(mid) >= x){
                right = mid;
            } else{
                left = mid + 1;
            }
        }
        return left;
    }
}
