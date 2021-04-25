package designpattern.behaviouralpattern.chainofresponsibility;

interface Chain {
    void setNext(Chain nextInChain);

    void process(Integer number);
} 


