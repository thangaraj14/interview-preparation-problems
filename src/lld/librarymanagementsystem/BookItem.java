package lld.librarymanagementsystem;

import java.time.LocalDate;
import java.util.Date;

public class BookItem extends Book {

    private String barcode;
    private boolean isReferenceOnly;
    private Date borrowed;
    private Date dueDate;
    private double price;
    private BookFormat format;
    private BookStatus status;
    private Date dateOfPurchase;
    private Date publicationDate;
    private Rack placedAt;

    public boolean checkout(Object object) {
        if (this.getIsReferenceOnly()) {
            showError("This book is Reference only and can't be issued");
            return false;
        }
        if (!BookLending.lendBook(this.getBarCode(), object)) {
            return false;
        }
        this.updateBookItemStatus(BookStatus.LOANED);
        return true;
    }

    String getBarCode() {
        return null;
    }

    private void showError(String string) {

    }

    private boolean getIsReferenceOnly() {
        return false;
    }

    public void updateBookItemStatus(BookStatus reserved) {
    }

    public void updateBookItemState(BookStatus reserved) {
    }

    public void updateDueDate(LocalDate plusDays) {
    }
}
