package lld.onlineshopping;
// For simplicity, we are not defining getter and setter functions. The reader can
// assume that all class attributes are private and accessed through their respective
// public getter methods and modified only through their public methods function.

import java.util.List;

public class Account {
    private String userName;
    private String password;
    private AccountStatus status;
    private String name;
    private Address shippingAddress;
    private String email;
    private String phone;

    private List<CreditCard> creditCards;

    public List<ElectronicBankTransfer> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<ElectronicBankTransfer> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    private List<ElectronicBankTransfer> bankAccounts;

    public void addProduct(Product product) {
    }

    public void addProductReview(ProductReview review) {
    }

    public void resetPassword() {
    }
}

