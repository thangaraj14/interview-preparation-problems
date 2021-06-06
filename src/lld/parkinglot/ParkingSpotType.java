package lld.parkinglot;

public enum ParkingSpotType {
    HANDICAPPED(5), COMPACT(4), LARGE(3), MOTORBIKE(2), ELECTRIC(1);
    public int numVal;

    ParkingSpotType(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }

}
