package lld.librarymanagementsystem;

import java.util.Date;

public class BookLending {

    private Date creationDate;
    private Date dueDate;
    private Date returnDate;
    private String bookItemBarcode;
    private String memberId;

    public static boolean lendBook(String barcode, Object object) {
        return false;
    }

    public static BookLending fetchLendingDetails(String barcode) {
        return null;
    }

    public Date getDueDate() {
        return null;
    }
}
