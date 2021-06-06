package dsa;

/**
 * http://www.geeksforgeeks.org/check-whether-a-given-string-is-an-interleaving-of-two-other-given-strings-set-2/
 * https://www.youtube.com/watch?v=ih2OZ9-M3OM&t=232s&ab_channel=TusharRoy-CodingMadeSimple
 */
public class TwoStringInterleavingToFormThird {

    public boolean isInterleaved(char[] str1, char[] str2, char[] str3) {

        boolean[][] T = new boolean[str1.length + 1][str2.length + 1];

        if (str1.length + str2.length != str3.length) {
            return false;
        }

        T[0][0] = true;
        for (int i = 0; i < T.length; i++) {
            for (int j = 0; j < T[i].length; j++) {
                int l = i + j - 1;
                // for first row, if match check left value
                if (i == 0) {
                    if (str1[j - 1] == str3[l]) {
                        T[i][j] = T[i][j - 1];
                    }
                    // for first column,if match check top value
                } else if (j == 0) {
                    if (str2[i - 1] == str3[l]) {
                        T[i][j] = T[i - 1][j];
                    }
                } else {
                    // if str1 matches check top value , str2 matches check left value
                    T[i][j] = (str1[i - 1] == str3[l] ? T[i - 1][j] : false) || (str2[j - 1] == str3[l] ?
                            T[i][j - 1] :
                            false);
                }
            }
        }
        return T[str1.length][str2.length];
    }

    public static void main(String args[]) {
        String str1 = "aab";
        String str2 = "axy";
        String str3 = "aaxaby";
        TwoStringInterleavingToFormThird sti = new TwoStringInterleavingToFormThird();
        System.out.println(sti.isInterleaved(str1.toCharArray(), str2.toCharArray(), str3.toCharArray()));
    }
}
