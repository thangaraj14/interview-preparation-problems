package strings.stringProblems;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/push-dominoes
 */
public class PushDominoes {

    /**
     * // if there is NO dot (meaning every domino is pushed), then the final state is the initial state
     * //   for example, all "LLL" => "LLL", all "RRR" => "RRR",
     * //                mixed "LLLRR" => "LLLRR" (falling domino having no effect on already-fallen domino)
     * // if there is only ONE dot, we have a few possibilities:
     * //   "L.R" => "L.R", "R.L" => "R.L" (center-one standing), "L.L" => "LLL", "R.R" => "RRR"
     * // if there are Three dots, we have the following possibilities:
     * //   "L..R" => "L..R", "R..L" => "RRLL" (center-one standing), "L..L" => "LLLL", "R..R" => "RRRR"
     * // if there are TWO dots, we have the following possibilities:
     * //   "L...R" => "L...R", "R...L" => "RR.LL" (center-one standing), "L...L" => "LLLLL", "R...R" => "RRRRR"
     * // Therefore the rule is:
     * // for each region of dots (a substring "P....Q"), check its left and right
     * //   if left is "L" and right is "R", the substring remains as is;
     * //   if left is "R" and right is "L", the substring is converted into either "RRRLLL" or "RR.LL"
     * //   if left and right are the same, the substring is converted into either "RRRRR" or "LLLLL"
     *
     * @param dominoes
     * @return
     */
    public String pushDominoes(String dominoes) {
        // place a sentinel "L" + dominoes + "R", as this does not impact the final outcome
        dominoes = "L" + dominoes + "R";
        char[] dom = dominoes.toCharArray();

        int i = 0; // slow pointer
        int j = 0;
        while (i < dominoes.length() - 1) {
            j = i + 1; // fast pointer
            while (j < dominoes.length() && dominoes.charAt(j) == '.') j++;

            if (dom[i] == dom[j]) Arrays.fill(dom, i, j, dom[i]);

            else if (dom[i] == 'R' && dom[j] == 'L') {
                int effect = (j - i - 1) / 2;
                /**
                 *                  // half R
                 *                 for (int count = 0; count < effect / 2; count++) {
                 *                     builder.append('R');
                 *                 }
                 *                 if (1 == countDots % 2) {
                 *                     // center dot
                 *                     builder.append('.');
                 *                 }
                 *                 // half L
                 *                 for (int count = 0; count < effect / 2; count++) {
                 *                     builder.append('L');
                 *                 }
                 */
                Arrays.fill(dom, i, i + effect + 1, 'R');
                Arrays.fill(dom, j - effect, j + 1, 'L');
            }

            i = j;
        }

        return new String(Arrays.copyOfRange(dom, 1, dom.length - 1));
    }

    public String pushDominoesAnother(String dominoes) {
        int[] leftForce = new int[dominoes.length()];
        int[] rightForce = new int[dominoes.length()];
        int lastSeen = -1;
        for (int i = dominoes.length() - 1; i >= 0; i--) {
            if (dominoes.charAt(i) == 'L') lastSeen = i;
            else if (dominoes.charAt(i) == 'R') lastSeen = -1;

            leftForce[i] = lastSeen;
        }
        lastSeen = -1;
        for (int i = 0; i < dominoes.length(); i++) {
            if (dominoes.charAt(i) == 'R') lastSeen = i;
            else if (dominoes.charAt(i) == 'L') lastSeen = -1;

            rightForce[i] = lastSeen;
        }
        char[] result = new char[dominoes.length()];

        for (int i = 0; i < dominoes.length(); i++) {
            if (dominoes.charAt(i) == '.') {

                int nearestLeft = leftForce[i];
                int nearestRight = rightForce[i];

                int leftDiff = nearestLeft == -1 ? Integer.MAX_VALUE : Math.abs(nearestLeft - i);
                int rightDiff = nearestRight == -1 ? Integer.MAX_VALUE : Math.abs(nearestRight - i);

                if (leftDiff == rightDiff) {
                    result[i] = '.';
                } else if (leftDiff < rightDiff) {
                    result[i] = 'L';
                } else if (leftDiff > rightDiff) {
                    result[i] = 'R';
                }

            } else {
                result[i] = dominoes.charAt(i);
            }

        }

        return new String(result);
    }
}
