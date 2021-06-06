package lld.atm;
// For simplicity, we are not defining getter and setter functions. The reader can

// assume that all class attributes are private and accessed through their respective
// public getter method and modified only through their public setter function.

public class Customer {
    private String name;
    private String email;
    private String phone;
    private Address address;
    private CustomerStatus status;

    private Card card;
    private Account account;

    public boolean makeTransaction(Transaction transaction) {
        return false;
    }

    public Address getBillingAddress() {
        return null;
    }
}
