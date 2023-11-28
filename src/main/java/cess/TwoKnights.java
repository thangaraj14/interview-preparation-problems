package cess;

/**
 * https://cses.fi/problemset/task/1072
 * <p>
 * https://www.youtube.com/watch?v=nKVubpav6Uk
 */
public class TwoKnights {

    public int getNumberOfWays(int n) {

        // number af all possibilities to place 2 knights on n*n board
        // binomial formula
        int numberOfAllPossibilities = (((n * n) * (n * n) - 1) / 2);

        //number Of rectangles Where two knights can attack each other
        int noOfRect = 4 * (n - 1) * (n - 2);

        // this return no of ways the two knights cannot attack each other
        return numberOfAllPossibilities - noOfRect;
    }
}
