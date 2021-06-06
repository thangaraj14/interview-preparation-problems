package lld.hotelroombooking;

import java.util.Date;
import java.util.List;

public class Room implements Search {
    private String roomNumber;
    private RoomStyle style;
    private RoomStatus status;
    private double bookingPrice;
    private boolean isSmoking;

    private List<RoomKey> keys;
    private List<RoomHouseKeeping> houseKeepingLog;

    public boolean isRoomAvailable() {
        return false;
    }

    public boolean checkIn() {
        return false;
    }

    public boolean checkOut() {
        return false;
    }

    public static List<Room> search(RoomStyle style, Date startDate, int duration) {
        return null;
        // return all rooms with the given style and availability
    }
}
