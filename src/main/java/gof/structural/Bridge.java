package gof.structural;

public class Bridge {
    public static void main(String[] args) {
        Vechicle bmwCar = new Car(new Bmw());
        Vechicle bmwTruck = new Truck(new Bmw());
        Vechicle mercedesCar = new Car(new Mercedes());
        bmwCar.drive();
        bmwTruck.drive();
        mercedesCar.drive();

    }
}

abstract class Vechicle{
    Model model;
    public Vechicle(Model model){
        this.model=model;
    }
    abstract void drive();
}

interface Model{
    void drive(String str);
}

class Car extends Vechicle {

    public Car(Model model) {
        super(model);
    }

    @Override
    void drive() {
        model.drive("drive car");
    }
}

class Truck extends Vechicle {

    public Truck(Model model) {
        super(model);
    }

    @Override
    void drive() {
        model.drive("drive truck");
    }
}

class Bmw implements Model {

    @Override
    public void drive(String str) {
        System.out.println(str+" BMW");
    }
}

class Mercedes implements Model {

    @Override
    public void drive(String str) {
        System.out.println(str+" Mercedes");
    }
}