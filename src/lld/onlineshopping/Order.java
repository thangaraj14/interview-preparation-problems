package lld.onlineshopping;

import java.util.Date;
import java.util.List;

public class Order {
    private String orderNumber;
    private OrderStatus status;
    private Date orderDate;
    private List<OrderLog> orderLog;

    public void sendForShipment() {
    }

    public void makePayment(Payment payment) {
    }

    public void addOrderLog(OrderLog orderLog) {
    }
}