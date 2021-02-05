package geeksforgeeks;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/boats-to-save-people/
 * <p>
 * Each boat carries at most 2 people at the same time,
 */
class BoatsToSave {

    public static int numRescueBoats(int[] people, int limit) {

        if (people.length == 0 || limit == 0) {
            return 0;
        }

        Arrays.sort(people);

        int i = 0;
        int j = people.length - 1;
        int result = 0;
        while (i <= j) {
            if (people[i] + people[j] <= limit) {
                result++;
                i++;
                j--;
            } else {
                result++;
                j--;// neglecting people with more weight 
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] people = new int[] { 1, 1, 1 };
        int limit = 3;
        numRescueBoats(people, limit);
    }
}