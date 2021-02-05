package geeksforgeeks;

/**
 * https://leetcode.com/problems/angle-between-hands-of-a-clock/
 */
public class AngleOfClock {

    public static double angleClock(int hours, int minutes) {
        // every hour is (360 degree /12 hrs in clock) = 30 degree
        // every min is (360 degree /60 mins per hr) = 6 degree
        // we take mod of 12 because 12th hr needs to be 0 degree
        double hour = (hours % 12 + (double) minutes / 60) * 30;
        double min = minutes * 6;
        double absAngle = Math.abs(hour - min);
        if (absAngle > 180) {
            absAngle = 360 - absAngle; // this is because when time is 0.02 the angel will be 358
        }
        return absAngle;
    }

    public static void main(String[] args) {
        angleClock(4, 30);
    }
}