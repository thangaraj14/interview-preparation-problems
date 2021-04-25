package practiceproblems;

public class AngleOfClock {
    public double angleClock(int hours, int minutes) {
        // every hour is 30(deg) (360 (deg)/12) because 12 hrs in clock
        // every min is 6(deg) (360(deg)/60) because 60 mins per hr
        // we take mod of 12 because 12th hr needs to be 0*
        double hourHand=  (hours%12 + (double)minutes/60)*30;
        double minHand= minutes*6;
        double absAngle= Math.abs(hourHand-minHand);
        if(absAngle>180) absAngle= 360-absAngle; // this is because when time is 0.02 the angel will be 358
        
        return absAngle;
    }    
}