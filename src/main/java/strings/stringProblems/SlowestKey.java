package strings.stringProblems;

/**
 * https://leetcode.com/problems/slowest-key/
 */
public class SlowestKey {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int index = 0;

        int duration = 0;
        int maxDuration = releaseTimes[0];

        for (int i = 1; i < releaseTimes.length; i++) {
            duration = releaseTimes[i] - releaseTimes[i - 1];

            if (duration > maxDuration || (maxDuration == duration && keysPressed.charAt(index) < keysPressed.charAt(i))) {

                maxDuration = duration;
                index = i;

            }

        }

        return keysPressed.charAt(index);
    }
}
