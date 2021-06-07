package gof.behaviouralpattern.state;

public class SoldOutState implements State {
    
    GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    public void insertRupee() {
        System.out.println("You can't insert a ruppee, the machine is sold out");
    }

    public void ejectRupee() {
        System.out.println("You can't eject, you haven't inserted a ruppee yet");
    }

    public void turnOn() {
        System.out.println("You turned, but there are no gumballs");
    }

    public void dispense() {
        System.out.println("No gumball dispensed");
    }

    public void refill() {
        gumballMachine.setState(gumballMachine.getNoRupeeState());
    }

    public String toString() {
        return "sold out";
    }
}
