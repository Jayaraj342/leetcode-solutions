import java.util.HashMap;
import java.util.Map;

abstract class Color implements Cloneable {
    protected String colorName;
    abstract void addColor();

    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}

class blueColor extends Color {
    public blueColor() {
        this.colorName = "blue";
    }

    @Override
    void addColor() {
        System.out.println("Blue color added");
    }

}

class blackColor extends Color {

    public blackColor() {
        this.colorName = "black";
    }

    @Override
    void addColor() {
        System.out.println("Black color added");
    }
}

class ColorStore {

    private static final Map<String, Color> colorMap = new HashMap<>();

    static {
        colorMap.put("blue", new blueColor());
        colorMap.put("black", new blackColor());
    }

    public static Color getColor(String colorName) {
        return (Color) colorMap.get(colorName).clone();
    }
}

class PrototypeDesignPattern {
    public static void main(String[] args) {
        Color blue1 = ColorStore.getColor("blue");
        blue1.addColor();
        System.out.println(blue1);

        Color black1 = ColorStore.getColor("black");
        black1.addColor();
        System.out.println(black1);

        Color blue2 = ColorStore.getColor("blue");
        blue2.addColor();
        System.out.println(blue2);

        Color black2 = ColorStore.getColor("black");
        black2.addColor();
        System.out.println(black2);
    }
}
