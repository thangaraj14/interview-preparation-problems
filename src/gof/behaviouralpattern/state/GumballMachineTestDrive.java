package behaviouralpattern.state;

public class GumballMachineTestDrive {

    public static void main(String[] args) {
        GumballMachine gumballMachine = new GumballMachine(10);

        System.out.println(gumballMachine);

        gumballMachine.insertRuppee();
        gumballMachine.turnOn();
        gumballMachine.insertRuppee();
        gumballMachine.turnOn();

        System.out.println(gumballMachine);

        gumballMachine.insertRuppee();
        gumballMachine.turnOn();
        gumballMachine.insertRuppee();
        gumballMachine.turnOn();

        System.out.println(gumballMachine);

        gumballMachine.insertRuppee();
        gumballMachine.turnOn();
        gumballMachine.insertRuppee();
        gumballMachine.turnOn();

        System.out.println(gumballMachine);

        gumballMachine.insertRuppee();
        gumballMachine.turnOn();
        gumballMachine.insertRuppee();
        gumballMachine.turnOn();

        System.out.println(gumballMachine);

        gumballMachine.insertRuppee();
        gumballMachine.turnOn();
        gumballMachine.insertRuppee();
        gumballMachine.turnOn();

        System.out.println(gumballMachine);
    }
}
