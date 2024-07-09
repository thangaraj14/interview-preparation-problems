package practiceproblems;

// the advantage of A with respect to B is the number of indices i for which A[i] > B[i].
/**
 * If the smallest card a in A beats the smallest card b in B, we should pair them.
 * Otherwise, a is useless for our score, as it can't beat any cards.
 * Why should we pair a and b if a > b?
 * Because every card in A is larger than b, any card we place in front of b will score a point.
 * We might as well use the weakest card to pair with b as it makes the rest of the cards in A strictly larger,
 * and thus have more potential to score points.
 */

import java.util.Arrays;
import java.util.PriorityQueue;

// Return any permutation of A that maximizes its advantage with respect to B.
//Input: A = [12,24,8,32], B = [13,25,32,11]
//Output: [24,32,8,12]
// Input: A = [2,7,11,15], B = [1,10,4,11]
// Output: [2,11,7,15]
public class AdvantageShuffle {
    public static int[] advantageCount(int[] A, int[] B) {
        Arrays.sort(A);

        var pq = new PriorityQueue<Integer[]>((a, b) -> Integer.compare(b[0], a[0]));
        for (int i = 0; i < B.length; i++) {
            pq.offer(new Integer[]{B[i], i}); // add elements of B along with its index to max queue
        }
        int[] result = new int[A.length]; // new placeholder for result
        int lo = 0;
        int hi = A.length - 1; // start and end index
        //B is transformed to [32,25,13,11]
        //A is transformed to  [8,12,24,32]
        while (!pq.isEmpty()) {
            var temp = pq.poll();
            int index = temp[1];
            int val = temp[0];
            /**
             * Two Rules:
             *      1) we should satisfy the biggest element first, because that's the hardest to satisfy
             *      2) If bigger element is not satisfiable we should put min element there and move on
             */
            if (A[hi] > val) { // if polled element is lesser thar A[hi], put A[hi] at index of
                // queued elements index, means, equal to B's current index we are putting
                // a value greater that B's in result arrays
                result[index] = A[hi--];
            } else {
                // else we put the lowest value at this position because no matter we try
                // we won't be getting a value larger than 'val'
                result[index] = A[lo++];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        advantageCount(new int[]{12,24,8,32}, new int[]{13,25,32,11});
    }
}