package dsa;

import java.util.Arrays;

/**
 * https://www.youtube.com/watch?v=CxpfBdPaRTE&ab_channel=GeeksforGeeks
 * https://www.youtube.com/watch?v=TWHytKnOPaQ&ab_channel=TusharRoy-CodingMadeSimple
 * <p>
 * http://www.geeksforgeeks.org/dynamic-programming-set-15-longest-bitonic-subsequence/
 * <p>
 * input : 1,11,2,10,4,5,2,1
 * output : 1,2,10,4,2,1
 */
public class BitonicSequence {

    public int longestSequence(int[] arr) {

        int[] lis = new int[arr.length];
        int[] lds = new int[arr.length];

        Arrays.fill(lis, 1);
        Arrays.fill(lds, 1);

        // Longest Increasing Subsequence left to Right
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }
        // Longest Increasing Subsequence Right to Left
        for (int i = arr.length - 2; i >= 0; i--) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[i] > arr[j]) {
                    lds[i] = Math.max(lds[i], lds[j] + 1);
                }
            }
        }
        // because that middle element is common in both sequence .. for example,
        // increasing subsequence 1,1,2,2,3,1,2,2 ..
        // decreasing subsequence 2,1,3,2,3,1,2,1  ..
        // bitonic subsequence    2,1,4,3,5,1,3,2
        // we can check no.of elements which is increasing if its 3 we have  two lesser elements on the left side and
        // same for right side also
        // here 5 is the maximum integer,so we have 3 on left and right, since its bitonic 3+3-1 = 5
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int i1 = lis[i] + lds[i] - 1;
            if (max < i1) {
                max = i1;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        BitonicSequence bs = new BitonicSequence();
        int[] arr = { 2, -1, 4, 3, 5, -1, 3, 2 };
        int r = bs.longestSequence(arr);
        System.out.println(r);

    }
}