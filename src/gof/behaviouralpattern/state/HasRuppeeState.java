package behaviouralpattern.state;

import java.util.Random;

public class HasRuppeeState implements State {
	Random randomWinner = new Random(System.currentTimeMillis());
	GumballMachine gumballMachine;

	public HasRuppeeState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

	public void insertRuppee() {
		System.out.println("You can't insert another ruppee");
	}

	public void ejectRuppee() {
		System.out.println("ruppee returned");
		gumballMachine.setState(gumballMachine.getNoRuppeeState());
	}

	public void turnOn() {
		System.out.println("You turned...");
		gumballMachine.setState(gumballMachine.getSoldState());
	}

	public void dispense() {
		System.out.println("No gumball dispensed");
	}

	public void refill() {
	}

	public String toString() {
		return "waiting for turn of crank";
	}
}
