package lld.onlineshopping;

import java.util.Date;

public abstract class Notification {
    private int notificationId;
    private Date createdOn;
    private String content;

    public void sendNotification(Account account) {
    }
}