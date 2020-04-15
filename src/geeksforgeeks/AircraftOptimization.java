package geeksforgeeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/discuss/interview-question/373202
 */
public class AircraftOptimization {

    public List<List<Integer>> calculateOptimalRoute(final List<List<Integer>> forwardList,
            final List<List<Integer>> returnList, int capacity) {

        Collections.sort(forwardList, Comparator.comparingInt(o -> o.get(1)));
        Collections.sort(returnList, Comparator.comparingInt(o -> o.get(1)));

        int max = 0;
        int i = 0;
        int j = returnList.size() - 1;

        List<List<Integer>> result = null;
        while (i < forwardList.size() && j >= 0) {
            int currentSum = forwardList.get(i).get(1) + returnList.get(j).get(1);

            if (currentSum > max && currentSum <= capacity) {
                max = currentSum;
                // Initializing new list
                result = new LinkedList<>();
                result.add(new ArrayList<>(Arrays.asList(forwardList.get(i).get(0), returnList.get(j).get(0))));
                i++;
            } else if (currentSum == max) {
                // no need to reset result list
                result.add(new ArrayList<>(Arrays.asList(forwardList.get(i).get(0), returnList.get(j).get(0))));
                i++;
            } else {
                j--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        AircraftOptimization aircraft = new AircraftOptimization();
        List<List<Integer>> returnList = new ArrayList<>();
        returnList.add(new ArrayList<Integer>(Arrays.asList(1, 2000)));
        returnList.add(new ArrayList<Integer>(Arrays.asList(2, 3000)));
        returnList.add(new ArrayList<Integer>(Arrays.asList(3, 4000)));
        returnList.add(new ArrayList<Integer>(Arrays.asList(4, 5000)));
        List<List<Integer>> forwardList = new ArrayList<>();
        forwardList.add(new ArrayList<Integer>(Arrays.asList(1, 3000)));
        forwardList.add(new ArrayList<Integer>(Arrays.asList(2, 5000)));
        forwardList.add(new ArrayList<Integer>(Arrays.asList(3, 7000)));
        forwardList.add(new ArrayList<Integer>(Arrays.asList(4, 10000)));
        List<List<Integer>> calculateOptimalRoute = aircraft.calculateOptimalRoute(forwardList, returnList, 10000);
        System.out.println(calculateOptimalRoute);
    }

}
