package microsoftassesment;

public class StringWithout3ConsequitiveLetter {

    // Minimum number of swaps to make string without any instance of 3 contiguous identical letters, swaps permitted a transforms to b and vice versa
    public int solution(String s) {


            // Corner Cases: s === null, s.length < 3 - return 0

            // Observations: for consecutive letter lengths divisible by 3 exactly, replace middle
            // if in between lengths from numbers divisible by 3, then replace the position in the divisible by 3 position
            // e.g. 3 consecutive - replace middle, 4 cons. - replace 3rd, 5 cons. - replace 3rd
            // 6 cons. - turns back in the 3 cons. case, therefore, replace middle

            // Time Complexity: O(n)
            // Space Complexity: O(1)

            if (s == null || s.length() < 3) {
                return 0;
            }
        int moves = 0;
        for (int i = 0 ; i < s.length(); i++) {
            int runLength = 1;
            for (; i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1); i++) {
                runLength++;
            }
            moves += runLength / 3;
        }
        return moves;
    }


}
