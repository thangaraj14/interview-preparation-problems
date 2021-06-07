package gof.behaviouralpattern.observer;

public class BackEndDeveloper implements Observer, DisplayElements {

	String profile;
	int experience;
	int salary;

	Subject subject;

	public BackEndDeveloper(Subject subject) {
		this.subject = subject;
		this.subject.registerObserver(this);
	}

	@Override
	public void update() {
		display();
	}

	void removeSubscription() {
		subject.removeObserver(this);
	}

	@Override
	public void display() {
		System.out.println("Current Conditions Display updated measurements");
	}

}
