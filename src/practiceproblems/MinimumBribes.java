package practiceproblems;
/**
 * https://www.hackerrank.com/challenges/new-year-chaos/problem
 * https://www.youtube.com/watch?v=UpmVTEvaXPE
 * Any person in the queue can bribe the person directly in front of them to swap positions.
 * If two people swap positions, they still wear the same sticker denoting their original place in line.
 * One person can bribe at most two other persons.
 * That is to say, if n = 8, and Person 5 bribes Person 4, the queue will look like this: 1,2,3,5,4,6,7,8.
 * Fascinated by this chaotic queue, you decide you must know the minimum number of bribes that took place to get the queue into its current state!
 *
 * No person can bribe more than 2 persons if yes then print chaotic
 * Input: 2 1 5 3 4
 * Output: 3
 *
 * Input: 2 5 1 3 4
 * Output: Too chaotic
 */
public class MinimumBribes {
     //  input is 2 1 5 3 4,
    // when index is at 2(val=>5) we see how many elements lesser than 5 is there
    // if the value is greater that 2 we print too chaotic
    // else we add it to result
    void minimumBribes(int[] q) {
        int bribes=0;
        for(int i = q.length-1; i >= 0; i--) {

            if(i-1>=0 && q[i-1]==i+1){
                q[i-1]=q[i];
                q[i]=i+1;
                bribes+=1;
            } else if(i-2>=0 && q[i-2]==i+2){
                //2,1,5,3,4 we need to swap 2 elements to restore the array
                q[i-2]=q[i-1];
                q[i-1]=q[i];
                q[i]=i+1;
                bribes+=2;
            }else{
                System.out.println("Too Chaotic");
                return;
            }
        }
        System.out.println(bribes);
    }

    public static void main(String[] args) {
        // inputs 5,1,2,3,7,8,6,4 => too chaotic
        //  1,2,5,3,7,8,6,4 => 7
        new MinimumBribes().minimumBribes(new int[]{2,1,5,3,4});
    }
}