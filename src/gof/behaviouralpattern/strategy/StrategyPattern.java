package gof.behaviouralpattern.strategy;

public class StrategyPattern {

	public static void main(String[] args) {
		Duck duck = new RubberDuck(new FlyNoWay(), new Squeak());
		duck.performFly();
		duck.performQuack();
	}

}
