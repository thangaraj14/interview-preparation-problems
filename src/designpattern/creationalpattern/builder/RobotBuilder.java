package creationalpattern.builder;
// Defines the methods needed for creating parts 

public interface RobotBuilder {

	public void buildRobotHead();
	public void buildRobotTorso();
	public void buildRobotArms();
	public void buildRobotLegs();
	public Robot getRobot();
}