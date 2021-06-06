package structuralpattern.bridge;

class Produce implements Workshop {

    @Override
    public void work() {
        System.out.print("Produced");
    }
}