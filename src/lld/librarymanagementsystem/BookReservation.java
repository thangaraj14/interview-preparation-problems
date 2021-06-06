package lld.librarymanagementsystem;

import java.util.Date;

public class BookReservation {

    private Date creationDate;
    private ReservationStatus status;
    private String bookItemBarcode;
    private String memberId;

    public static BookReservation fetchReservationDetails(String barcode) {
        return null;
    }

    public Object getMemberId() {
        return null;
    }

    public void updateStatus(ReservationStatus completed) {

    }

    public void sendBookAvailableNotification() {

    }

    public void decrementTotalBooksCheckedout() {

    }
}

