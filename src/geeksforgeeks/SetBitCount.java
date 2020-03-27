package geeksforgeeks;

/**
 * https://leetcode.com/problems/number-of-1-bits/discuss/55099/Simple-Java-Solution-Bit-Shifting
 */
public class SetBitCount {
    public int hammingWeight(int n) {
        int count = 0;

        while (n != 0) {
            n = n & (n - 1);
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        int input = 00000000000000000000000000001011;
    }
}
