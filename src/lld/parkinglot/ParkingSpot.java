package lld.parkinglot;

public class ParkingSpot {
    private String number;
    private boolean free;
    private Vehicle vehicle;
    private final ParkingSpotType type;

    public boolean IsFree() {
        return false;
    }

    public ParkingSpot(ParkingSpotType type) {
        this.type = type;
    }

    public boolean assignVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        return free = false;
    }

    public boolean removeVehicle() {
        this.vehicle = null;
        return free = true;
    }

    public String getNumber() {
        return null;
    }

    public int getType() {
        return 0;
    }
}
