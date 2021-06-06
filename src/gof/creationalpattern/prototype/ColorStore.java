package gof.creationalpattern.prototype;

import java.util.HashMap;
import java.util.Map;

class ColorStore {

    public static void setColorMap(Map<String, Color> colorMap) {
        ColorStore.colorMap = colorMap;
    }

    public static Map<String, Color> getColorMap() {
        return colorMap;
    }

    static Map<String, Color> colorMap = new HashMap<>();

    public static Color getColor(String colorName) {
        return (Color) colorMap.get(colorName).clone();
    }
}