package behaviouralpattern.strategy;

public class RubberDuck extends Duck {

	public RubberDuck(FlyBehaviour fly, QuackBehaviour quack) {
		flyBehaviour = fly;
		quackBehaviour = quack;
	}

	@Override
	public void display() {
		System.out.println("Its Rubber Duck");
	}

}
