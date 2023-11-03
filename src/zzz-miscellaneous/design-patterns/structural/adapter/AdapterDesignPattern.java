interface Movable {
    // returns speed in MPH
    double getSpeed();
}

class BugattiVeyron implements Movable {
    @Override
    public double getSpeed() {
        return 268;
    }
}

class MovableAdapter implements Movable {
    private final Movable luxuryCars;

    MovableAdapter(Movable luxuryCars) {
        this.luxuryCars = luxuryCars;
    }

    @Override
    public double getSpeed() {
        return convertMPHtoKMPH(luxuryCars.getSpeed());
    }

    private double convertMPHtoKMPH(double mph) {
        return mph * 1.60934;
    }
}

class Car {
    public static void printSpeed(Movable movable) {
        System.out.println(movable.getSpeed());
    }
}

class AdapterDesignPattern {
    public static void main(String[] args) {
        Movable bugattiVeyron = new BugattiVeyron();
        Movable bugattiVeyronAdapter = new MovableAdapter(bugattiVeyron);

        Car.printSpeed(bugattiVeyron);
        Car.printSpeed(bugattiVeyronAdapter);
    }
}

// https://www.baeldung.com/java-adapter-pattern