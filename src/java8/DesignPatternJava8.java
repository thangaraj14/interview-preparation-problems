package java8;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class DesignPatternJava8 {
    public static void main(String[] args) {
        ArrayListIteratorPattern listFn= new ArrayListIteratorPattern(5);
        listFn.setElements(new Object[]{1,2,3,4,5,6,7});
        listFn.forEach(System.out::println);

        Burger bur=new BurgerShop(burger -> burger.addCheese()).use(new BurgerShop(Burger::addVeggies).use(new Burger()));
        System.out.println(bur);
    }
}

class ArrayListIteratorPattern {
    Object[] elements ;
    public ArrayListIteratorPattern(int n){
        elements = new Object[n];
    }

    public void setElements(Object[] elements){
        this.elements=elements;
    }

    public  void forEach(Consumer consumer){
        for (Object element : elements) {
            consumer.accept(element);
        }
    }
}

 class Burger {

    private String burgerType;

    public Burger() {

        this.burgerType = "";
    }

    private Burger(String type) {
        this.burgerType = type;
    }

    public Burger addVeggies() {
        System.out.println("Adding vegies to the burger");
        return new Burger(this.burgerType += " Veggie");
    }

    public Burger addCheese() {
        System.out.println("Adding cheese to the burger");
        return new Burger(this.burgerType += " Cheese");
    }

    public String toString() {
        return String.format("%s", burgerType + " burger");
    }

}

class BurgerShop {

    UnaryOperator< Burger> decoration;

    public BurgerShop(UnaryOperator<Burger> decoration) {
        this.decoration = decoration;
    }

    public Burger use(Burger baseBurger) {
        System.out.println("Base Burger : " + baseBurger);
        return decoration.apply(baseBurger);
    }

}
