package gof.behaviouralpattern.chainofresponsibility;

class TestChain {
    
    public static void main(String[] args) {

        Chain c1 = new NegativeProcessor();
        Chain c2 = new ZeroProcessor();
        Chain c3 = new PositiveProcessor();
        c1.setNext(c2);
        c2.setNext(c3);

        c1.process(90);
        c1.process(-50);
        c1.process(0);
        c1.process(91);
    }
}