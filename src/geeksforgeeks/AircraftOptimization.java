package geeksforgeeks;

import java.util.*;

/**
 * https://leetcode.com/discuss/interview-question/373202
 * Given 2 lists a and b. Each element is a pair of integers where the first integer represents the unique id and the second integer represents a value. 
 * Your task is to find an element from a and an element form b such that the sum of their values is less or equal to target and as close to target as possible.
 * Return a list of ids of selected elements. If no pair is possible, return an empty list.
 * 
 * a = [[1, 2], [2, 4], [3, 6]]
   b = [[1, 2]]
   target = 7

   Output: [[2, 1]]

    Explanation:
    There are only three combinations [1, 1], [2, 1], and [3, 1], which have a total sum of 4, 6 and 8, respectively.
    Since 6 is the largest sum that does not exceed 7, [2, 1] is the optimal pair.
 */
public class AircraftOptimization {

    public List<List<Integer>> calculateOptimalRoute(final List<List<Integer>> forwardList,
            final List<List<Integer>> returnList, int capacity) {

        forwardList.sort(Comparator.comparingInt(o->o.get(1)));
        returnList.sort(Comparator.comparingInt(o->o.get(1)));

        int max = 0;
        int i = 0;
        int j = returnList.size() - 1;

        List<List<Integer>> result = new LinkedList<>();
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
        returnList.add(new ArrayList<>(Arrays.asList(1, 2000)));
        returnList.add(new ArrayList<>(Arrays.asList(2, 3000)));
        returnList.add(new ArrayList<>(Arrays.asList(3, 4000)));
        returnList.add(new ArrayList<>(Arrays.asList(4, 5000)));
        List<List<Integer>> forwardList = new ArrayList<>();
        forwardList.add(new ArrayList<>(Arrays.asList(1, 3000)));
        forwardList.add(new ArrayList<>(Arrays.asList(2, 5000)));
        forwardList.add(new ArrayList<>(Arrays.asList(3, 7000)));
        forwardList.add(new ArrayList<>(Arrays.asList(4, 10000)));
        List<List<Integer>> calculateOptimalRoute = aircraft.calculateOptimalRoute(forwardList, returnList, 10000);
        System.out.println(calculateOptimalRoute);
    }
}
