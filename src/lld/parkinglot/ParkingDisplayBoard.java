package lld.parkinglot;

public class ParkingDisplayBoard {
    private String id;
    private HandicappedSpot handicappedFreeSpot;
    private CompactSpot compactFreeSpot;
    private LargeSpot largeFreeSpot;
    private MotorbikeSpot motorbikeFreeSpot;
    private ElectricSpot electricFreeSpot;

    public void showEmptySpotNumber() {
        String message = "";
        if (handicappedFreeSpot.IsFree()) {
            message += "Free Handicapped: " + handicappedFreeSpot.getNumber();
        } else {
            message += "Handicapped is full";
        }
        message += System.lineSeparator();

        if (compactFreeSpot.IsFree()) {
            message += "Free Compact: " + compactFreeSpot.getNumber();
        } else {
            message += "Compact is full";
        }
        message += System.lineSeparator();

        if (largeFreeSpot.IsFree()) {
            message += "Free Large: " + largeFreeSpot.getNumber();
        } else {
            message += "Large is full";
        }
        message += System.lineSeparator();

        if (motorbikeFreeSpot.IsFree()) {
            message += "Free Motorbike: " + motorbikeFreeSpot.getNumber();
        } else {
            message += "Motorbike is full";
        }
        message += System.lineSeparator();

        if (electricFreeSpot.IsFree()) {
            message += "Free Electric: " + electricFreeSpot.getNumber();
        } else {
            message += "Electric is full";
        }

        Show(message);
    }

    private void Show(String message) {
        // TODO Auto-generated method stub

    }

    public HandicappedSpot getHandicappedFreeSpot() {
        // TODO Auto-generated method stub
        return null;
    }

    public void setHandicappedFreeSpot(HandicappedSpot handicappedSpot) {
        // TODO Auto-generated method stub

    }

    public HandicappedSpot getCompactFreeSpot() {
        // TODO Auto-generated method stub
        return null;
    }

    public void setCompactFreeSpot(CompactSpot compactSpot) {
        // TODO Auto-generated method stub

    }
}