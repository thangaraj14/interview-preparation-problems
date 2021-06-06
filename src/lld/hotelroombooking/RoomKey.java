package lld.hotelroombooking;

import java.util.Date;

public class RoomKey {
    private String keyId;
    private String barcode;
    private Date issuedAt;
    private boolean active;
    private boolean isMaster;

    public boolean assignRoom(Room room) {
        return false;
    }

    public boolean isActive() {
        return false;
    }
}
