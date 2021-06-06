package dsa;

import java.util.*;

public class GenerateRandomString {

    static String getAlphaNumericString(int n) {

        int lowerLimit = 97;
        int upperLimit = 122;

        Random random = new Random();

        StringBuilder r = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int nextRandomChar = lowerLimit + random.nextInt(upperLimit - lowerLimit + 1);
            r.append((char) nextRandomChar);
        }

        return r.toString();
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(getAlphaNumericString(n));
    }
}
