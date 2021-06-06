package lld.restauranttablebooking;
// For simplicity, we are not defining getter and setter functions. The reader can

// assume that all class attributes are private and accessed through their respective
// public getter methods and modified only through their public setter function.

public abstract class Account {
    private String id;
    private String password;
    private Address address;
    private AccountStatus status;

    public boolean resetPassword() {
        return false;
    }
}
