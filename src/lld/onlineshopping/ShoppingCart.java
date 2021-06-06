package lld.onlineshopping;

import java.util.List;

public class ShoppingCart {

    private List<Item> items;

    public void addItem(Item item) {
    }

    public void removeItem(Item item) {
    }

    public void updateItemQuantity(Item item, int quantity) {
        item.updateQuantity(quantity);
    }

    public List<Item> getItems() {
        return null;
    }

    public Order checkout() {
        //calculate amount
        // create order
        //return order

        return null;
    }
}
