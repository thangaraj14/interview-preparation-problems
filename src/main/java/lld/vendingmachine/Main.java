package lld.vendingmachine;

public class Main {
    public static void main(String[] args) {
        System.out.println("State Design Pattern Vending Machine.");
        System.out.println("Vending Machine activated by Machine States (HasMoney, NoMoney, Sold, SoldOut).\n");

        VendingMachine vendingMachine = new VendingMachine(2);
        System.out.println(vendingMachine);
        System.out.println();

        vendingMachine.insertMoney();
        System.out.println(vendingMachine);
        System.out.println();

        vendingMachine.selectProduct();
        System.out.println(vendingMachine);
        System.out.println();

        vendingMachine.dispenseProduct();
        System.out.println(vendingMachine);
        System.out.println();


        vendingMachine.insertMoney();
        System.out.println(vendingMachine);
        System.out.println();

        vendingMachine.selectProduct();
        System.out.println(vendingMachine);
        System.out.println();

        vendingMachine.dispenseProduct();
        System.out.println(vendingMachine);
        System.out.println();


        vendingMachine.insertMoney();
        System.out.println(vendingMachine);
        System.out.println();

        vendingMachine.selectProduct();
        System.out.println(vendingMachine);
        System.out.println();

        vendingMachine.dispenseProduct();
        System.out.println(vendingMachine);
        System.out.println();
    }
}