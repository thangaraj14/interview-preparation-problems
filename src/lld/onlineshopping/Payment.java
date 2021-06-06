package lld.onlineshopping;

class CreditCard extends Payment {

}

class ElectronicBankTransfer extends Payment {

}

public abstract class Payment {

    PaymentStatus paymentStatus;
    double amount;

    void processPayment() {
        //this.amount
    }

}
