// Abstract vs interface
interface Area {
    double getArea(int param);

    default int defaultArea(int a) {
        return a * a;
    }

    static int getPerimeter(int param) {
        return param * 4;
    }
}

abstract class Area2 {
    abstract double getArea(int param);

    // default methods are because all classes need not implement them - else abstract method's will have to be implemented by all classes
    int defaultArea(int a) {
        return a * a;
    }

    static int getPerimeter(int param) {
        return param * 4;
    }
}

//Only one abstract method
@FunctionalInterface
interface Area {
    double getArea(int param);

    // default methods are because all classes need not implement them - else abstract method's will have to be implemented by all classes
    default int defaultArea(int a) {
        return a * a;
    }

    static int getPerimeter(int param) {
        return param * 4;
    }
}

class Main {
    static final Area circle = (radius) -> Math.PI * Math.pow(radius, 2);
    static final Area square = (length) -> Math.pow(length, 2);

    public static void main(String[] args) {
        System.out.println(area(5, circle));
        System.out.println(area(10, square));
    }

    private static double area(int param, Area quadrilateral) {
        System.out.println(quadrilateral.defaultArea(10));
        return quadrilateral.getArea(param);
    }
}

class Pentagon implements Area {

    public static void main(String[] args) {
        System.out.println(Area.getPerimeter(10));
        System.out.println(Pentagon.getPerimeter(10));
    }

    @Override
    public double getArea(int param) {
        return 1.72 * param * param;
    }

    // not compulsory
    @Override
    public int defaultArea(int a) {
        return Area.super.defaultArea(a);
    }

    public static int getPerimeter(int a) {
        return 5 * a;
    }
}

class FunctionInterfaceTest {
    static Consumer<Integer> consumer = (val) -> System.out.println(val * val);
    static Supplier<Double> supplier = Math::random;
    static Predicate<Integer> predicate = (val) -> val > 0;
    static Function<Integer, Double> function = (r) -> Math.PI * r * r;

    public static void main(String[] args) {
        consumer.accept(10);
        System.out.println(supplier.get());
        System.out.println(predicate.test(-1));
        System.out.println(function.apply(1));
    }
}

//Inner class
class Main1 {
    static class Main2 {
        private static final int param = 10;

        public void printParam() {
            System.out.println(param);
        }
    }
}

class Main {
    public static void main(String[] args) {
        new Main().test();
    }

    private void test() {
        Main1.Main2 main2 = new Main1.Main2();
        main2.printParam();
    }
}

interface Age {
    int x = 21;
    void getAge();
}

//AnonymousClass
class AnonymousClass {
    public static void main(String[] args) {
        Age age = new Age() {
            @Override
            public void getAge() {
                System.out.println(this.x);
            }
        };
        age.getAge();
    }
}
