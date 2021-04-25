package thread.pluralsight.second;


/*
intput : 3
output :  1 2 3
          8 9 4
          7 6 5*/

/* null / empty */

public class TestSoftwareObj {

    //    1. create an array with input's size
    //    2.

    // col = 2
    // row = 2

/*
    00 01 02
    12 22
    21 20
    11  12

 */

/*    1  2 3 4
      12 13  14    5
      11 16   15    6
      10 9 8 7
      */
    // fill first row : i=0       &&     j = i+1
    // fill last column: i=col-1  &&    j=col
    // fill last row : i=row      &&    j= row-1
    // fill middle row : i=row-1  &&    col=0

    //   input = 4

    //   00 01 02 03      |||     11 12
    //   13 23 33          ||    22
    //   32 31 30          ||
    //   20 10             ||      31

    // 4/2 = 2+1 = 3
    // 3/2 = 1+1 = 2
    public static void main(String[] args) {

        TestSoftwareObj testSoftwareObj = new TestSoftwareObj();
        testSoftwareObj.printSpiralMatrix(3);

    }

    private void printSpiralMatrix(int num) {

        int rowStart = 0;
        int colStart = 0;
        int rowEnd = num - 1;
        int colEnd = num - 1;
        int cond = num / 2;
        int index = 0;

        int[][] arr = new int[num][num];
        while (cond >= 0) {
            // row wise right to left
            for (int i = rowStart; i <=rowEnd; i++) {
                arr[0][i] = ++index;
            }
            rowEnd /= 2;
            rowStart++;

            // last column
            for (int i = rowStart; i <= colEnd; i++) {
                arr[i][colEnd] = ++index;
            }

            colEnd /= 2;

            // row end wise
            for (int i = colEnd; i >= 0; i--) {
                arr[colEnd][i] = ++index;
            }

            // first column
            for (int i = rowEnd; i > rowStart; i--) {
                arr[i][colStart] = ++index;
            }




            cond--;
        }
    }

}
