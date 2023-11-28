package cess;


import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * https://www.youtube.com/watch?v=KOD2BFauQbA
 * A Gray code is a list of all 2n
 * bit strings of length n
 * , where any two successive strings differ in exactly one bit (i.e., their Hamming distance is one).
 * Your task is to create a Gray code for a given length n
 * <p>
 * <p>
 * Input:
 * 2
 * <p>
 * Output:
 * 00 -|
 * 01 -| - difference 1
 * 11
 * 10
 */
public class GreyCode {

    public static List<String> greyCode(int n) {

        if (n == 0) return new ArrayList<>();
        if (n == 1) {
            List<String> baseRes = new ArrayList<>();
            baseRes.add("0");
            baseRes.add("1");
            return baseRes;
        }

        List<String> intermediateResult = greyCode(n - 1);
        List<String> finalResult = new ArrayList<>();
        for (String s : intermediateResult) {
            finalResult.add("0" + s);
        }

        for (int i = intermediateResult.size() - 1; i >= 0; i--) {
            finalResult.add("1" + intermediateResult.get(i));
        }
        return finalResult;

    }

    public static List<String> greyCode1(int n) {
        List<String> finalResult = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            int val = i ^ (i >> 1);

            // If len = 4 and val = 1,
            // Integer.toBinaryString( (1 << len) | val ) => returns the string "10001", then
            // "10001".substring( 1 )  discards the very first character. So we obtain what we want:
            // "0001"
            finalResult.add(Integer.toBinaryString((1 << n) | val).substring(1));
        }
        return finalResult;
    }

    public static void main(String[] args) {
        BitSet b = new BitSet();
        greyCode(3).forEach(System.out::println);
        System.out.println();
        greyCode1(3).forEach(System.out::println);
    }
}
