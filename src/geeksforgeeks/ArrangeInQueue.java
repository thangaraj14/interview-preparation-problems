package geeksforgeeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Input:
// [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

//after sorting : [[7,0], [7,1],[6,1], [5,0], [5,2],[4,4]]

// Output:
// [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

/**
 * https://leetcode.com/problems/queue-reconstruction-by-height/
 */
public class ArrangeInQueue {

    // 1.   Sort people by their height, shortest to tallest
    // 2.   Iterate and put each person to the correct position
    // 2.a  When placing the shortest person, all person to his left will be taller or equal height, since you are iterating in height sorted array, so put it at a index equal to its k value
    // 2.b  When placing the next shortest person, find a position, where count of positions to the left unoccupied plus the ones where same height person is placed, is equal to its k value
    // 2.c  Keep repeating
    
    public int[][] reconstructQueue(int[][] people) {
        List<int[]> result = new ArrayList<>();
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });

        for (int[] x : people) {
            result.add(x[1], x);
        }
        return result.toArray(new int[people.length][2]);
    }
}