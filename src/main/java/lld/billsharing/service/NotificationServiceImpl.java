package lld.billsharing.service;


import lld.billsharing.model.Expense;
import lld.billsharing.model.User;

public class NotificationServiceImpl implements NotificationService {
    @Override
    public void notifyUser(User user, Expense expense) {
        System.out.println("Notified");
    }
}
