package gof.behaviouralpattern.observer;

public class FrontEndDeveloper implements Observer, DisplayElements {
    Subject publisher;

    String profile;
    int experience;
    int salary;

    public FrontEndDeveloper(Subject subject) {
        this.publisher = subject;
        this.publisher.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Statistics data updated");
    }

    void removeWeatherSubscription() {
        publisher.removeObserver(this);
    }

    @Override
    public void update() {
        display();
    }

}
