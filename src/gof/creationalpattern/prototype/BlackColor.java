package gof.creationalpattern.prototype;

class BlackColor extends Color {

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    int value;

    public BlackColor() {
        this.colorName = "black";
        this.value = 100;
    }

    @Override
    void addColor() {
        System.out.println("Black color added");
    }
}