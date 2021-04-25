package practiceproblems;

import java.util.List;

/**
 * A binary matrix means that all elements are 0 or 1. For each individual row of the matrix, this row is sorted in non-decreasing order.
 *
 * Given a row-sorted binary matrix binaryMatrix, return leftmost column index(0-indexed) with at least a 1 in it. If such index doesn’t exist, return -1.
 *
 * You can’t access the Binary Matrix directly.  You may only access the matrix using a BinaryMatrix interface:
 *
 * BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
 * BinaryMatrix.dimensions() returns a list of 2 elements [rows, cols], which means the matrix is rows * cols.
 * Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer.
 * Also, any solutions that attempt to circumvent the judge will result in disqualification
 */
interface BinaryMatrix {
     public int get(int row, int col);
    public List<Integer> dimensions();
  }
public class LeftMostColumnWithOne {

    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimension=binaryMatrix.dimensions();
        int n=dimension.get(0);
        int m=dimension.get(1);

        int i=0,j=m-1,leftMostOne=-1; // start from 0th row and last column

        while(i<n && j>=0)
        {
            int result=binaryMatrix.get(i,j);
            if(result==0)
                i++;
            else{
                leftMostOne=j;
                j--;
            }
        }
        return leftMostOne;
    }
}
