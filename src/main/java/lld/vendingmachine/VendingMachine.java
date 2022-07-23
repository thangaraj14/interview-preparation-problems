package lld.vendingmachine;

public class VendingMachine {
    private VendingMachineState hasMoneyState;
    private VendingMachineState noMoneyState;
    private VendingMachineState soldOutState;
    private VendingMachineState soldState;
    private VendingMachineState machineState;

    private int numberOfProducts;

    //Vending Machine Constructor:
    public VendingMachine(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
        hasMoneyState = new HasMoneyState(this);
        noMoneyState = new NoMoneyState(this);
        soldOutState = new SoldOutState(this);
        soldState = new SoldState(this);
        if (numberOfProducts > 0) {
            machineState = noMoneyState;
        }
    }

    //Getters and Setters:
    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public VendingMachineState getHasMoneyState() {
        return hasMoneyState;
    }

    public VendingMachineState getNoMoneyState() {
        return noMoneyState;
    }

    public VendingMachineState getSoldOutState() {
        return soldOutState;
    }

    public VendingMachineState getSoldState() {
        return soldState;
    }

    public VendingMachineState getMachineState() {
        return machineState;
    }

    public void setState(VendingMachineState state) {
        machineState = state;
    }

    //Vending Machine Actions:
    public void insertMoney() {
        machineState.insertMoney();
    }

    public void ejectMoney() {
        machineState.ejectMoney();
    }

    public void selectProduct() {
        machineState.selectProduct();
    }

    public void dispenseProduct() {
        machineState.dispenseProduct();
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("Welcome to Vending Machine.\n");
        result.append("Inventory:\t" + numberOfProducts + " product");
        if (numberOfProducts != 1) {
            result.append("s.\n");
        } else {
            result.append(".\n");
        }
        result.append("Vending Machine State:\t" + machineState + "\n");
        return result.toString();
    }
}