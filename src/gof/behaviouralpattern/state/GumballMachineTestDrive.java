package gof.behaviouralpattern.state;

public class GumballMachineTestDrive {

    public static void main(String[] args) {
        
        GumballMachine gumballMachine = new GumballMachine(10);

        System.out.println(gumballMachine);

        gumballMachine.insertRupee();
        gumballMachine.turnOn();
        gumballMachine.insertRupee();
        gumballMachine.turnOn();

        System.out.println(gumballMachine);

        gumballMachine.insertRupee();
        gumballMachine.turnOn();
        gumballMachine.insertRupee();
        gumballMachine.turnOn();

        System.out.println(gumballMachine);

        gumballMachine.insertRupee();
        gumballMachine.turnOn();
        gumballMachine.insertRupee();
        gumballMachine.turnOn();

        System.out.println(gumballMachine);

        gumballMachine.insertRupee();
        gumballMachine.turnOn();
        gumballMachine.insertRupee();
        gumballMachine.turnOn();

        System.out.println(gumballMachine);

        gumballMachine.insertRupee();
        gumballMachine.turnOn();
        gumballMachine.insertRupee();
        gumballMachine.turnOn();

        System.out.println(gumballMachine);
    }
}
