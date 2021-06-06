package gof.creationalpattern.prototype;

class BlueColor extends Color {

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    int value;

    public BlueColor() {
        this.colorName = "blue";
        this.value = 99;
    }

    @Override
    void addColor() {
        System.out.println("Blue color added");
    }

}