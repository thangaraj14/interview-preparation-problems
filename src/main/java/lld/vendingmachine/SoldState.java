package lld.vendingmachine;

public class SoldState implements VendingMachineState {
	private VendingMachine vendingMachine;
	
	public SoldState(VendingMachine vendingMachine) { this.vendingMachine = vendingMachine; }
	
	@Override
	public void insertMoney() {
		System.out.println("\tProduct is Ready.");
	}

	@Override
	public void ejectMoney() {
		System.out.println("\tProduct is is Already Coming Out.");
		
	}

	@Override
	public void selectProduct() {
		System.out.println("\tProduct is Already Selected.");		
	}

	@Override
	public void dispenseProduct() {
		
		if(vendingMachine.getNumberOfProducts() > 0) {
			System.out.println("\tProduct is Being Dispensed.");		
			vendingMachine.setNumberOfProducts(vendingMachine.getNumberOfProducts()-1);
			vendingMachine.setState(vendingMachine.getNoMoneyState());
		}
		else {
			System.out.println("\tOut of Products.");
			vendingMachine.setState(vendingMachine.getSoldOutState());
			vendingMachine.ejectMoney();
		}
	}

	@Override
	public String toString() {
		return "\tDispensing Product State.";
	}
}