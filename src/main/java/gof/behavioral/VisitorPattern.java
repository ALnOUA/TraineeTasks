package gof.behavioral;

import lombok.extern.java.Log;

public class VisitorPattern {
    public static void main(String[] args) {
        Vechicle car = new Car();
        car.drive(new ConcreteVisitors());

    }
}

interface Vechicle{
    void drive(Visitor visitor);

}

class Car implements Vechicle {

    @Override
    public void drive(Visitor visitor) {
        visitor.driveCar();
    }
}

class Truck implements Vechicle {

    @Override
    public void drive(Visitor visitor) {
        visitor.driveTruck();
    }
}

interface Visitor{
    void driveCar();
    void driveTruck();
}

@Log
class ConcreteVisitors implements Visitor {
    @Override
    public void driveCar() {
        log.info("drive car");
    }

    @Override
    public void driveTruck() {
        log.info("drive truck");
    }
}
