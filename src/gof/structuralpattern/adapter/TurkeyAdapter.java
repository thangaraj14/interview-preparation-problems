package structuralpattern.adapter;

public class TurkeyAdapter implements Duck {

	Turkey turkey;

	public TurkeyAdapter(Turkey turkey) {
		this.turkey = turkey;
	}

	@Override
	public void fly() {
		for (int i = 0; i < 5; i++) {
			turkey.fly();
		}
	}

	@Override
	public void quack() {
		turkey.gobble();

	}
}
