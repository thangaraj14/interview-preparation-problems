package bitwise;

// https://leetcode.com/problems/bitwise-and-of-numbers-range/
public class BitwiseRange {


    // https://www.youtube.com/watch?v=j3XRFREnPWI
    // the reason here is there is a common prefix between the two numbers
    // we find the common prefix and then shift it to the left
    // this is a property of bitwise and

    public static int rangeBitwiseAnd(int left, int right) {
        int shift = 0;
        System.out.println("le: " + Integer.toBinaryString(left));
        System.out.println("ri: " + Integer.toBinaryString(right));
        System.out.println("#########");
        while (left < right) {
            left >>= 1;
            right >>= 1;
            System.out.println("le : " + Integer.toBinaryString(left));
            System.out.println("ri : " + Integer.toBinaryString(right));
            shift++;
        }

        return left << shift;
    }

    public static void main(String[] args) {
        System.out.println(rangeBitwiseAnd(1024, 512));
    }
}
