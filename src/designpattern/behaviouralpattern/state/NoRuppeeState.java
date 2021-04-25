package behaviouralpattern.state;

public class NoRuppeeState implements State {
    GumballMachine gumballMachine;

    public NoRuppeeState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    public void insertRuppee() {
        System.out.println("You inserted a ruppee");
        gumballMachine.setState(gumballMachine.getHasRuppeeState());
    }

    public void ejectRuppee() {
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
