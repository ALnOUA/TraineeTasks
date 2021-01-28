package collections.arraylist;

import lombok.extern.java.Log;

@Log
public class ExampleArray {
    public static void main(String[] args) throws ArrayIndexOutOfBoundException {

        MyArrayList cars = new MyArrayList();
        cars.add("BMW");
        cars.add("Skoda");
        cars.add("Mercedes");
        cars.add("Fiat");
        cars.add("Siat");
        cars.add("Ford");
        log.info(cars.get(2));
        cars.remove(2);
        for (int i=0; i<cars.getSize()-1;i++){
            log.info(cars.get(i));
        }
        cars.get(12);


    }

}
