package lld.onlineshopping;

public class Member extends Customer {
    private Account account;

    public void placeOrder(Order order) {
        order.makePayment(this.account.getBankAccounts().get(0));
    }

}