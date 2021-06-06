package behaviouralpattern.observer;

public interface Subject {

    void removeObserver(Observer observer);

    void notifyObservers();

    void registerObserver(Observer observer);

}
