package behaviouralpattern.state;

public class GumballMachine {

    State soldOutState;
    State noRuppeeState;
    State hasRuppeeState;
    State soldState;
    State winnerState;

    State state = soldOutState;
    int count = 0;

    public GumballMachine(int numberGumballs) {
        noRuppeeState = new NoRuppeeState(this);
        hasRuppeeState = new HasRuppeeState(this);
        soldState = new SoldState(this);
        soldOutState = new SoldOutState(this);

        this.count = numberGumballs;
        if (numberGumballs > 0) {
            state = noRuppeeState;
        }
    }

    public void insertRuppee() {
        state.insertRuppee();
    }

    public void ejectRuppee() {
        state.ejectRuppee();
    }

    public void turnOn() {
        state.turnOn();
        state.dispense();
    }

    void setState(State state) {
        this.state = state;
    }

    void releaseBall() {
        System.out.println("A gumball comes rolling out the slot...");
        if (count != 0) {
            count = count - 1;
        }
    }

    int getCount() {
        return count;
    }

    void refill(int count) {
        this.count += count;
        System.out.println("The gumball machine was just refilled; it's new count is: " + this.count);
        state.refill();
    }

    public State getState() {
        return state;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getNoRuppeeState() {
        return noRuppeeState;
    }

    public State getHasRuppeeState() {
        return hasRuppeeState;
    }

    public State getSoldState() {
        return soldState;
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("\nMighty Gumball, Inc.");
        result.append("\nJava-enabled Standing Gumball Model #2004");
        result.append("\nInventory: " + count + " gumball");
        if (count != 1) {
            result.append("s");
        }
        result.append("\n");
        result.append("Machine is " + state + "\n");
        return result.toString();
    }
}
