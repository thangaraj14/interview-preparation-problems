package behaviouralpattern.observer;

public class JobOpenings {

    public static void main(String[] args) {

        JobConsultancy consultant = new JobConsultancy();

        BackEndDeveloper back = new BackEndDeveloper(consultant);

        consultant.notifyObservers();
        back.removeSubscription();
        consultant.notifyObservers();

    }

}
