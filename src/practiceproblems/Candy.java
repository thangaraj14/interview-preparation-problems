package practiceproblems;

import java.util.Arrays;

/**
 * https://www.hackerrank.com/challenges/candies/problem
 * Alice wants to give at least 1 candy to each child.
 * If two children sit next to each other, then 
 * the one with the higher rating must get more candies than neighbour (left and right).
 * Alice wants to minimize the total number of candies she must buy.

   For example, assume her students' ratings are                  [4, 6, 4, 5, 6, 2].
   She gives the students candy in the following minimal amounts: [1, 2, 1, 2, 3, 1]. She must buy a minimum of 10 candies.
 */
class Candy {

    // 2, 4, 2, 6, 1, 7, 8, 3, 2, 1
    // 1, 2, 1, 2, 1, 2, 3, 1, 1, 1
    //             1, 2, 4, 3, 2, 1
    int candy(int[] ratings) {
        int size = ratings.length;
        if (size <= 1) {
            return size;
        }

        // ideally we should maintain 2 arrays 1)L->R 2) R->L
        // then iterate both and check max b/w 2 items as result for each index
        // we optimise it further for extra space and for loop 
        int[] num = new int[size];
        Arrays.fill(num, 1);
        // left to right
        for (int i = 1; i < size; i++) {
            if (ratings[i] > ratings[i - 1]) {
                num[i] = num[i - 1] + 1;
            }
        }

        // right to left
        for (int i = size - 1; i > 0; i--) {
            if (ratings[i - 1] > ratings[i]) {
                // check curr index + 1 or existing value
                num[i - 1] = Math.max(num[i] + 1, num[i - 1]);
            }
        }
        int result = 0;
        for (int i = 0; i < size; i++) {
            result += num[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Candy candy = new Candy();

        int[] ratings = { 2, 4, 2, 6, 1, 7, 8, 9, 2, 1 };
        System.out.println(candy.candy(ratings));
    }
}