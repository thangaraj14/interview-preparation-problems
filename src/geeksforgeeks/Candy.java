package geeksforgeeks;

import java.util.Arrays;

/**
 * https://www.hackerrank.com/challenges/candies/problem
 */
class Candy {

    // 2, 4, 2, 6, 1, 7, 8, 3, 2, 1
    // 1, 2, 1, 2, 1, 2, 3, 1, 1, 1
    //             1,2,4,3,2,1
    int candy(int[] ratings) {
        int size = ratings.length;
        if (size <= 1)
            return size;

        int[] num = new int[size];
        Arrays.fill(num, 1);
        // left to righ
        for (int i = 1; i < size; i++) {
            if (ratings[i] > ratings[i - 1])
                num[i] = num[i - 1] + 1;
        }

        // right to left
        for (int i = size - 1; i > 0; i--) {
            if (ratings[i - 1] > ratings[i])
                num[i - 1] = Math.max(num[i] + 1, num[i - 1]);
        }
        int result = 0;
        for (int i = 0; i < size; i++) {
            result += num[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Candy candy = new Candy();

        int[] ratings = {2, 4, 2, 6, 1, 7, 8, 9, 2, 1};
        System.out.println(candy.candy(ratings));
    }
}