package dsa;

/**
 * 010(2)
 * 001(1) 011
 * 100(4) 111
 * 101(5) 010
 * 010(2) 000
 * 100(4) 100
 * 001(1) 101 ------final answer
 */
public class SingleNumber {

    static int singleNumber(int A[]) {

        int result = 0;
        for (int val : A) {
            result ^= val;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[] { 1, 2, 4, 5, 2, 4, 1 }));
    }
}
