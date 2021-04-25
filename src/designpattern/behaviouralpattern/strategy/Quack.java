package behaviouralpattern.strategy;

public class Quack implements QuackBehaviour {

	@Override
	public void quack() {
		System.out.println("Quack Quack");
	}

}
