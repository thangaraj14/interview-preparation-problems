package lld.parkinglot;

import java.util.HashMap;

public class ParkingFloor {
    private String name;
    private HashMap<String, HandicappedSpot> handicappedSpots;
    private HashMap<String, CompactSpot> compactSpots;
    private HashMap<String, LargeSpot> largeSpots;
    private HashMap<String, MotorbikeSpot> motorbikeSpots;
    private HashMap<String, ElectricSpot> electricSpots;
    private HashMap<String, CustomerInfoPortal> infoPortals;
    private ParkingDisplayBoard displayBoard;

    public ParkingFloor(String name) {
        this.name = name;
    }

    public void addParkingSpot(MotorbikeSpot spot) {
		/*switch (spot.getType()) {
		case ParkingSpotType.HANDICAPPED:
			handicappedSpots.put(spot.getNumber(), spot);
			break;
		case ParkingSpotType.COMPACT.getNumVal():
			compactSpots.put(spot.getNumber(), spot);
			break;
		case ParkingSpotType.LARGE.getNumVal():
			largeSpots.put(spot.getNumber(), spot);
			break;
		case ParkingSpotType.MOTORBIKE.getNumVal():
			motorbikeSpots.put(spot.getNumber(), spot);
			break;
		case ParkingSpotType.ELECTRIC.getNumVal():
			electricSpots.put(spot.getNumber(), spot);
			break;
		default:
			print("Wrong parking spot type!");
		}*/
    }

    private void print(String string) {

    }

    public void assignVehicleToSpot(Vehicle vehicle, ParkingSpot spot) {
        spot.assignVehicle(vehicle);
		/*switch (spot.getType()) {
		case ParkingSpotType.HANDICAPPED:
			updateDisplayBoardForHandicapped(spot);
			break;
		case ParkingSpotType.COMPACT.getNumVal():
			updateDisplayBoardForCompact(spot);
			break;
		case ParkingSpotType.LARGE.getNumVal():
			updateDisplayBoardForCompact(spot);
			break;
		case ParkingSpotType.MOTORBIKE.getNumVal():
			updateDisplayBoardForCompact(spot);
			break;
		case ParkingSpotType.ELECTRIC.getNumVal():
			updateDisplayBoardForCompact(spot);
			break;
		default:
			print("Wrong parking spot type!");
		}*/
    }

    private void updateDisplayBoardForHandicapped(ParkingSpot spot) {
        if (this.displayBoard.getHandicappedFreeSpot().getNumber() == spot.getNumber()) {
            // find another free handicapped parking and assign to displayBoard
            for (String key : handicappedSpots.keySet()) {
                if (handicappedSpots.get(key).IsFree()) {
                    this.displayBoard.setHandicappedFreeSpot(handicappedSpots.get(key));
                }
            }
            this.displayBoard.showEmptySpotNumber();
        }
    }

    private void updateDisplayBoardForCompact(ParkingSpot spot) {
        if (this.displayBoard.getCompactFreeSpot().getNumber() == spot.getNumber()) {
            // find another free compact parking and assign to displayBoard
            for (String key : compactSpots.keySet()) {
                if (compactSpots.get(key).IsFree()) {
                    this.displayBoard.setCompactFreeSpot(compactSpots.get(key));
                }
            }
            this.displayBoard.showEmptySpotNumber();
        }
    }

    public void freeSpot(ParkingSpot spot) {
        spot.removeVehicle();
        int freeElectricSpotCount = 0;
	/*	switch (spot.getType()) {
		case ParkingSpotType.HANDICAPPED:
			freeElectricSpotCount++;
			break;
		case ParkingSpotType.COMPACT.getNumVal():
			int freeCompactSpotCount = 0;
			freeCompactSpotCount++;
			break;
		case ParkingSpotType.LARGE.getNumVal():
			int freeLargeSpotCount = 0;
			freeLargeSpotCount++;
			break;
		case ParkingSpotType.MOTORBIKE.getNumVal():
			int freeMotorbikeSpotCount = 0;
			freeMotorbikeSpotCount++;
			break;
		case ParkingSpotType.ELECTRIC.getNumVal():
			freeElectricSpotCount++;
			break;
		default:
			print("Wrong parking spot type!");
		}*/
    }

    public boolean isFull() {
        return false;
    }
}