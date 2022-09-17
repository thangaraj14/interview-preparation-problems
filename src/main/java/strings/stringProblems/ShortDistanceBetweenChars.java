package strings.stringProblems;

/**
 * https://leetcode.com/problems/shortest-distance-to-a-character
 */
public class ShortDistanceBetweenChars {

    public int[] shortestToChar(String s, char c) {

        int[] result = new int[s.length()];
        //Before the first C value is reached, there is no index behind it to compute a difference.
        //So letting lastSeen = Integer.MAX_VALUE assures that the computed difference on the first pass before the first C value is reached is higher than what would be computed on the way back.
        int lastSeen = Integer.MAX_VALUE;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                lastSeen = i;
            }

            result[i] = Math.abs(lastSeen - i);
        }

        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == c) {
                lastSeen = i;
            }
            result[i] = Math.min(result[i], Math.abs(lastSeen - i));
        }

        return result;
    }
}
