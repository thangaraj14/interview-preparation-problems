package lld.vendingmachine;

public class NoMoneyState implements VendingMachineState {
	private VendingMachine vendingMachine;
	
	public NoMoneyState(VendingMachine vendingMachine) { this.vendingMachine = vendingMachine; }
	
	@Override
	public void insertMoney() {
		System.out.println("\tMoney is Inserted.");
		vendingMachine.setState(vendingMachine.getHasMoneyState());
	}

	@Override
	public void ejectMoney() {
		System.out.println("\tNo Money.");
		vendingMachine.setState(vendingMachine.getNoMoneyState());
	}

	@Override
	public void selectProduct() {
		System.out.println("\tSelection Made. No Money.");
		
	}

	@Override
	public void dispenseProduct() {
		System.out.println("\tNo Disperse before Money Insertion.");
	}

	@Override
	public String toString() {
		return "\tNo Money State, Waiting for Money.";
	}
}