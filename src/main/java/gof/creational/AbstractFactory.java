package gof.creational;

import lombok.extern.java.Log;

public class AbstractFactory {
    public static void main(String[] args) {
        AbstractFurnitureFactory abstractFurnitureFactory = new AbstractFurnitureFactory();
        abstractFurnitureFactory.createFactory("Modern").createChair("Wood").sitOn();
        abstractFurnitureFactory.createFactory("Modern").createChair("Iron").sitOn();
        abstractFurnitureFactory.createFactory("Victorian").createChair("Iron").sitOn();
        abstractFurnitureFactory.createFactory("Victorian").createChair("Wood").sitOn();
        abstractFurnitureFactory.createFactory("Victorian").createSofa("Silver").sitOn();
        abstractFurnitureFactory.createFactory("Modern").createSofa("Gold").sitOn();

    }
}

interface Chair{
    void sitOn();
    void hasLegs();
}

interface Sofa{
    void sitOn();
    void hasLegs();
}

@Log
class VictorianGoldSofa implements Sofa {

    @Override
    public void sitOn() {
        log.info("sit on victorian gold sofa");
    }

    @Override
    public void hasLegs() {
        log.info("has 4 legs");
    }
}

@Log
class VictorianSilverSofa implements Sofa {

    @Override
    public void sitOn() {
        log.info("sit on victorian silver sofa");
    }

    @Override
    public void hasLegs() {
        log.info("has 4 legs");
    }
}

@Log
class ModernGoldSofa implements Sofa {

    @Override
    public void sitOn() {
        log.info("sit on modern gold sofa");
    }

    @Override
    public void hasLegs() {
        log.info("has 4 legs");
    }
}

@Log
class ModernSilverSofa implements Sofa {

    @Override
    public void sitOn() {
        log.info("sit on modern silver sofa");
    }

    @Override
    public void hasLegs() {
        log.info("has 4 legs");
    }
}

@Log
class VictorianWoodChair implements Chair {

    @Override
    public void sitOn() {
        log.info("sit on victorian wood chair");
    }

    @Override
    public void hasLegs() {
        log.info("has 4 legs");
    }
}

@Log
class VictorianIronChair implements Chair {

    @Override
    public void sitOn() {
        log.info("sit on victorian iron chair");
    }

    @Override
    public void hasLegs() {
        log.info("has 4 legs");
    }
}

@Log
class ModernWoodChair implements Chair {

    @Override
    public void sitOn() {
        log.info("sit on modern wood chair");
    }

    @Override
    public void hasLegs() {
        log.info("has 4 legs");
    }
}

@Log
class ModernIronChair implements Chair {

    @Override
    public void sitOn() {
        log.info("sit on modern iron chair");
    }

    @Override
    public void hasLegs() {
        log.info("has 4 legs");
    }
}

interface FurnitureFactory{
    Chair createChair(String typeOfChair);
    Sofa createSofa(String typeOfSofa);
}

class VictorianFactory implements FurnitureFactory {

    @Override
    public Chair createChair(String typeOfChair) {
        switch (typeOfChair){
            case "Wood": return new VictorianWoodChair();
            case "Iron": return new VictorianIronChair();
            default: return null;
        }
    }

    @Override
    public Sofa createSofa(String typeOfSofa) {
        switch (typeOfSofa){
            case "Gold": return new VictorianGoldSofa();
            case "Silver": return new VictorianSilverSofa();
            default: return null;
        }
    }
}

class ModernFactory implements FurnitureFactory {

    @Override
    public Chair createChair(String typeOfChair) {
        switch (typeOfChair){
            case "Wood": return new ModernWoodChair();
            case "Iron": return new ModernIronChair();
            default: return null;
        }
    }

    @Override
    public Sofa createSofa(String typeOfSofa) {
        switch (typeOfSofa){
            case "Gold": return new ModernGoldSofa();
            case "Silver": return new ModernSilverSofa();
            default: return null;
        }
    }
}

class AbstractFurnitureFactory{
    FurnitureFactory createFactory(String typeOfFactory){
        switch (typeOfFactory) {
            case "Victorian": return new VictorianFactory();
            case "Modern": return new ModernFactory();
            default:return null;
        }
    }

}


