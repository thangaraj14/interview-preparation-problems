package gof.creationalpattern.prototype;

class Prototype {

    public static void main(String[] args) {

        BlueColor blueColor = new BlueColor();
        ColorStore.getColorMap().put("blue", blueColor);

        BlueColor blue = (BlueColor) ColorStore.getColor("blue");
        blue.addColor();

        blue.setValue(1000);

        System.out.println(blueColor.getValue());
        System.out.println(blue.getValue());

    }
}