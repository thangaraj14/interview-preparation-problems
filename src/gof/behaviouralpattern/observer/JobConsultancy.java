package behaviouralpattern.observer;

import java.util.ArrayList;

public class JobConsultancy implements Subject {

    private ArrayList<Observer> subscriptionList;

    String profile;
    int experience;
    int salary;

    public JobConsultancy() {
        subscriptionList = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        subscriptionList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int index = subscriptionList.indexOf(observer);
        if (!subscriptionList.isEmpty()) {
            subscriptionList.remove(index);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : subscriptionList) {
            observer.update(profile, experience, salary);
        }
    }
}
