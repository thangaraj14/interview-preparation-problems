package lld.hotelroombooking;

import java.util.Date;

public abstract class RoomCharge {
    public Date issueAt;

    public boolean addInvoiceItem(Invoice invoice) {
        return false;
    }
}
