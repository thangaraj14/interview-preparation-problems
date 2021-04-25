package behaviouralpattern.observer;

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
	public void update(String profile, int experience, int salary) {
		this.profile = profile;
		this.experience = experience;
		this.salary = salary;
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
