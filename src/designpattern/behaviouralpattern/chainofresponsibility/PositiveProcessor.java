package designpattern.behaviouralpattern.chainofresponsibility;

class PositiveProcessor implements Chain {

    private Chain nextInChain;

    @Override
    public void setNext(Chain c) {
        nextInChain = c;
    }

    @Override
    public void process(Integer number) {
        if (number > 0) {
            System.out.println("PositiveProcessor : " + number);
        } else {
            nextInChain.process(number);
        }
    }
}