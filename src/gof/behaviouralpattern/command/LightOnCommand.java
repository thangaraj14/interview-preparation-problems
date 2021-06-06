package behaviouralpattern.command;

//Concrete Command
public class LightOnCommand implements Command {

    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.switchOn();
    }
}