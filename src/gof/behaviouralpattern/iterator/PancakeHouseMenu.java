package behaviouralpattern.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PancakeHouseMenu implements Menu {
    ArrayList<MenuItem> menuItems;

    public PancakeHouseMenu() {
        menuItems = new ArrayList<>();

        addItem("Blueberry Pancakes", "Pancakes made with fresh blueberries, and blueberry syrup", true, 3.49);

        addItem("Waffles", "Waffles, with your choice of blueberries or strawberries", true, 3.59);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        menuItems.add(menuItem);
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public Iterator<MenuItem> createIterator() {
        return menuItems.iterator();
    }

}
