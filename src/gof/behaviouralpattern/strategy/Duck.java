package gof.behaviouralpattern.strategy;

public abstract class Duck {

	QuackBehaviour quackBehaviour;
	FlyBehaviour flyBehaviour;

	public abstract void display();

	public void performFly() {
		flyBehaviour.fly();
	}

	public void performQuack() {
		quackBehaviour.quack();
	}
}
