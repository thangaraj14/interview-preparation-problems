package geeksforgeeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://www.interviewbit.com/problems/maximum-unsorted-subarray/#
public class MaximumUnsortedSubarray {

    public static ArrayList<Integer> subarraySort(ArrayList<Integer> A) {

        ArrayList<Integer> list = new ArrayList<>();
        int start = -1;
        int end = -1;

        // from left
        for (int i = 1; i < A.size(); ++i) {
            if (A.get(i) < A.get(i - 1)) {
                start = i - 1;
                break;
            }
        }

        // fully sorted
        if (start == -1) {
            list.add(-1);
            return list;
        }

        // from right
        for (int i = A.size() - 2; i >= 0; --i) {
            if (A.get(i) > A.get(i + 1)) {
                end = i + 1;
                break;
            }
        }

        // find min and max in the range [start, end]
        int min = A.get(start);
        int max = A.get(start);
        for (int i = start; i <= end; ++i) {
            min = Math.min(min, A.get(i));
            max = Math.max(max, A.get(i));
        }

        for (int i = 0; i < start; ++i) {
            if (A.get(i) > min) {
                start = i;
                break;
            }
        }

        for (int i = A.size() - 1; i >= end + 1; --i) {
            if (A.get(i) < max) {
                end = i;
                break;
            }
        }

        list.add(start);
        list.add(end);

        return list;
    }

    public static void main(String[] args) {
        //1, 1, 10, 10, 15, 10, 15, 10,10, 15, 10, 15
        //(1, 3, 2, 4, 5)));
        //4, 15, 4, 4, 15, 18, 20
        List<Integer> result = subarraySort(new ArrayList<>(Arrays.asList(4, 15, 4, 4, 15, 18, 20)));
        result.stream().forEach(System.out::println);
    }
}
