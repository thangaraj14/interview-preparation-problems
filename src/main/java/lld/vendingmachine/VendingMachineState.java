package lld.vendingmachine;

public interface VendingMachineState {
	 public void insertMoney();
	
	 public void ejectMoney();
	 
	 public void selectProduct();
	 
	 public void dispenseProduct();
}