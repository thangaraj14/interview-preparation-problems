package lld.stackoverflow;
// For simplicity, we are not defining getter and setter functions. The reader can
// assume that all class attributes are private and accessed through their respective
// public getter methods and modified only through their public methods function.

import lld.cricinfo.Address;

public class Account {
    private String id;
    private String password;
    private AccountStatus status;
    private String name;
    private Address address;
    private String email;
    private String phone;
    private int reputation;

    public void resetPassword() {
    }
}

