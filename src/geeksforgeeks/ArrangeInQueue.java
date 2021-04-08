package geeksforgeeks;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//You are given an array of people, people,
// which are the attributes of some people in a queue (not necessarily in order).
// Each people[i] = [hi, ki] represents the ith person of height hi with exactly ki
// other people in front who have a height greater than or equal to hi.
//Reconstruct and return the queue that is represented by the input array people.
// The returned queue should be formatted as an array queue,
// where queue[j] = [hj, kj] is the attributes of the jth person in the
// queue (queue[0] is the person at the front of the queue).

// Input:
// [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
// Output:
// [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
//Explanation:
// Person 0 has height 5 with no other people taller or the same height in front.
// Person 1 has height 7 with no other people taller or the same height in front.
// Person 2 has height 5 with two persons taller or the same height in front, which is person 0 and 1.
// Person 3 has height 6 with one person taller or the same height in front, which is person 1.
// Person 4 has height 4 with four people taller or the same height in front, which are people 0, 1, 2, and 3.
// Person 5 has height 7 with one person taller or the same height in front, which is person 1.
// Hence [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] is the reconstructed queue.
public class ArrangeInQueue {

// 1.   Sort people by their height, shortest to tallest 
// 2.   Iterate and put each person to the correct position 
// 2.a  When placing the shortest person, all person to his left will be taller or equal height, since you are iterating in height sorted array, so put it at a index equal to its k value 
// 2.b  When placing the next shortest person, find a position, where count of positions to the left unoccupied plus the ones where same height person is placed, is equal to its k value 
// 2.c  Keep repeating
    public static int[][] reconstructQueue(int[][] people) {
        List<int[]> result= new ArrayList<>();
        Arrays.sort(people,(a, b)->{
           if(a[0]==b[0]) return a[1]-b[1];
           else return b[0]-a[0];
        });
        System.out.println("Sorted values: "+ Arrays.deepToString(people));
        for(int[] x: people){
            result.add(x[1],x);
            System.out.println(Arrays.deepToString(result.toArray(new int[people.length][2])));
        }
        return result.toArray(new int[people.length][2]);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(reconstructQueue(new int[][]{{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}})));
    }
}