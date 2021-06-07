package gof.behaviouralpattern.state;

public class NoRupeeState implements State {
    GumballMachine gumballMachine;

    public NoRupeeState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    public void insertRupee() {
        System.out.println("You inserted a ruppee");
        gumballMachine.setState(gumballMachine.getHasRupeeState());
    }

    public void ejectRupee() {
        System.out.println("You haven't inserted a ruppee");
    }

    public void turnOn() {
        System.out.println("You turned, but there's no ruppee");
    }

    public void dispense() {
        System.out.println("You need to pay first");
    }

    public void refill() {
    }

    public String toString() {
        return "waiting for quarter";
    }
}
