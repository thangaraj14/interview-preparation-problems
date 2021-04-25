package designpattern.structuralpattern.bridge;

// abstraction in bridge pattern 
abstract class Vehicle {

    public abstract void manufacture();

    protected Workshop workShop1;
    protected Workshop workShop2;

    protected Vehicle(Workshop workShop1, Workshop workShop2) {
        this.workShop1 = workShop1;
        this.workShop2 = workShop2;
    }

}
