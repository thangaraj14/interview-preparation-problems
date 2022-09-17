package lld.vendingmachine;

public class SoldOutState implements VendingMachineState {
	@SuppressWarnings("unused")
	private VendingMachine vendingMachine;
	
	public SoldOutState(VendingMachine vendingMachine) { this.vendingMachine = vendingMachine; }
	
	@Override
	public void insertMoney() {
		System.out.println("\tProducts Sold Out");
	}

	@Override
	public void ejectMoney() {
		System.out.println("\tMoney is Rejected Back to Customer.");		
	}

	@Override
	public void selectProduct() {
		System.out.println("\tProduct Not Available.");		
	}

	@Override
	public void dispenseProduct() {
		System.out.println("\tProduct Sold Out.");		
	}

	@Override
	public String toString() {
		return "\tProduct Sold Out State.";
	}
}