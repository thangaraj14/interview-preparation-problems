package gof.behaviouralpattern.state;

public class SoldState implements State {

    GumballMachine gumballMachine;

    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    public void insertRupee() {
        System.out.println("Please wait, we're already giving you a gumball");
    }

    public void ejectRupee() {
        System.out.println("Sorry, you already turned the crank");
    }

    public void turnOn() {
        System.out.println("Turning twice doesn't get you another gumball!");
    }

    public void dispense() {
        gumballMachine.releaseBall();
        if (gumballMachine.getCount() > 0) {
            gumballMachine.setState(gumballMachine.getNoRupeeState());
        } else {
            System.out.println("Oops, out of gumballs!");
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }
    }

    public void refill() {
    }

    public String toString() {
        return "dispensing a gumball";
    }
}
