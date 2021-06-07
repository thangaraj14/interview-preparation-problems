package gof.behaviouralpattern.command;

//Client
public class Client {

    public static void main(String[] args) {

        Light light = new Light();
        Command lightsOn = new LightOnCommand(light);
        Command lightsOff = new LightOffCommand(light);

        RemoteControl control = new RemoteControl();

        // switch on
        control.setCommand(lightsOn);
        control.pressButton();

        // switch off
        control.setCommand(lightsOff);
        control.pressButton();
    }
}