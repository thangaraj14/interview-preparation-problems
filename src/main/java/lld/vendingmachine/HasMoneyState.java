package lld.vendingmachine;

public class HasMoneyState implements VendingMachineState {
	private VendingMachine vendingMachine;
	
	public HasMoneyState(VendingMachine vendingMachine) { this.vendingMachine = vendingMachine; }
	
	@Override
	public void insertMoney() {
		System.out.println("\tMoney is Inserted.");		
	}

	@Override
	public void ejectMoney() {
		System.out.println("\tRejecting Money.");
		vendingMachine.setState(vendingMachine.getNoMoneyState());		
	}

	@Override
	public void selectProduct() {
		System.out.println("\tItem is Selected.");
		vendingMachine.setState(vendingMachine.getSoldState());		
	}

	@Override
	public void dispenseProduct() {
		System.out.println("\tDispensing Product.");
	}

	@Override
	public String toString() {
		return "\tMoney Accepted State, Waiting for Product Selection.";
	}
}