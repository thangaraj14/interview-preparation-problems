package lld.billsharing.service;

import lld.billsharing.model.Expense;
import lld.billsharing.model.User;

public interface NotificationService {
    void notifyUser(User user, Expense expense);
}
