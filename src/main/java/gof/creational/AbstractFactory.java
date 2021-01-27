package gof.creational;

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

class VictorianGoldSofa implements Sofa {

    @Override
    public void sitOn() {
        System.out.println("sit on victorian gold sofa");
    }

    @Override
    public void hasLegs() {
        System.out.println("has 4 legs");
    }
}

class VictorianSilverSofa implements Sofa {

    @Override
    public void sitOn() {
        System.out.println("sit on victorian silver sofa");
    }

    @Override
    public void hasLegs() {
        System.out.println("has 4 legs");
    }
}

class ModernGoldSofa implements Sofa {

    @Override
    public void sitOn() {
        System.out.println("sit on modern gold sofa");
    }

    @Override
    public void hasLegs() {
        System.out.println("has 4 legs");
    }
}

class ModernSilverSofa implements Sofa {

    @Override
    public void sitOn() {
        System.out.println("sit on modern silver sofa");
    }

    @Override
    public void hasLegs() {
        System.out.println("has 4 legs");
    }
}

class VictorianWoodChair implements Chair {

    @Override
    public void sitOn() {
        System.out.println("sit on victorian wood chair");
    }

    @Override
    public void hasLegs() {
        System.out.println("has 4 legs");
    }
}

class VictorianIronChair implements Chair {

    @Override
    public void sitOn() {
        System.out.println("sit on victorian iron chair");
    }

    @Override
    public void hasLegs() {
        System.out.println("has 4 legs");
    }
}

class ModernWoodChair implements Chair {

    @Override
    public void sitOn() {
        System.out.println("sit on modern wood chair");
    }

    @Override
    public void hasLegs() {
        System.out.println("has 4 legs");
    }
}

class ModernIronChair implements Chair {

    @Override
    public void sitOn() {
        System.out.println("sit on modern iron chair");
    }

    @Override
    public void hasLegs() {
        System.out.println("has 4 legs");
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


