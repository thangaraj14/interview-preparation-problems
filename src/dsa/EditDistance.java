package dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Date 07/07/2014
 *
 * @author Tushar Roy
 * <p>
 * Given two strings how many minimum edits(update, delete or add) is
 * needed to convert one string to another
 * <p>
 * Time complexity is O(m*n) Space complexity is O(m*n)
 * <p>
 * References:
 * http://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/
 * https://en.wikipedia.org/wiki/Edit_distance
 */
public class EditDistance {

    /**
     * Uses bottom up DP to find the edit distance
     */
    public int dynamicEditDistance(char[] str1, char[] str2) {
        int[][] temp = new int[str1.length + 1][str2.length + 1];

        for (int i = 0; i < temp[0].length; i++) {
            temp[0][i] = i;
        }

        for (int i = 0; i < temp.length; i++) {
            temp[i][0] = i;
        }

        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    temp[i][j] = temp[i - 1][j - 1];
                } else {
                    temp[i][j] = 1 + min(temp[i - 1][j - 1], temp[i - 1][j], temp[i][j - 1]);
                }
            }
        }
        System.out.println(Arrays.deepToString(temp));
        printActualEdits(temp, str1, str2);
        return temp[str1.length][str2.length];

    }

    /**
     * Prints the actual edits which needs to be done.
     */
    public void printActualEdits(int T[][], char[] str1, char[] str2) {
        int i = T.length - 1;
        int j = T[0].length - 1;
        while (true) {
            if (i == 0 || j == 0) {
                break;
            }
            if (str1[i - 1] == str2[j - 1]) {
                i = i - 1;
                j = j - 1;
            } else if (T[i][j] == T[i - 1][j - 1] + 1) {
                System.out.println("Edit " + str2[j - 1] + " in string2 to " + str1[i - 1] + " in string1");
                i = i - 1;
                j = j - 1;
            } else if (T[i][j] == T[i - 1][j] + 1) {
                System.out.println("Delete in string1 " + str1[i - 1]);
                i = i - 1;
            } else if (T[i][j] == T[i][j - 1] + 1) {
                System.out.println("Delete in string2 " + str2[j - 1]);
                j = j - 1;
            } else {
                throw new IllegalArgumentException("Some wrong with given data");
            }
        }
    }

    private int min(int a, int b, int c) {
        int l = Math.min(a, b);
        return Math.min(l, c);
    }

    public static void main(String[] args) {
        String str1 = "horse";
        String str2 = "ros";
        EditDistance editDistance = new EditDistance();
        int result = editDistance.dynamicEditDistance(str1.toCharArray(), str2.toCharArray());
        System.out.print(result);
    }

    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<Integer, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] arr = str.toCharArray();
            int sum = 0;
            for (char ch : arr) {
                sum += ch - 'a';
            }

            if (map.containsKey(sum)) {
                map.get(sum).add(str);
            } else {
                map.put(sum, new ArrayList<>(Arrays.asList(str)));
            }
        }
        return new ArrayList<>(map.values());
    }

}