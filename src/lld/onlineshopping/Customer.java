package lld.onlineshopping;

import java.util.List;

public abstract class Customer {
    private ShoppingCart cart;
    private Order order;
    private Catalog catalog;

    public List<Item> getShoppingCart() {
        return cart.getItems();
    }

    public void checkout() {
        Order order = cart.checkout();
        this.order = order;
    }

    public void addItemToCart(Item item) {
        cart.addItem(item);
    }

    public void removeItemFromCart(Item item) {
    }

    public List<Product> searchProduct(String item) {
        return catalog.searchProductsByCategory(item);
    }

    public void chooseProduct() {
        Item item = new Item();

    }

}
