package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTask {
    public static void main(String[] args) {
        int[] array = {1,3,53,2,6654,43,2340,-234,-34,-1,0,32,87,65,82};
        IntStream.of(array).sorted().filter(x->x%2==0).forEach(System.out::println);
        IntStream.of(array).sorted().filter(x->x%2==0).map(value->value*2).forEach(System.out::println);
        IntStream.of(array).sorted().parallel().filter(x->x%2==0).map(value->value*2).forEachOrdered(System.out::println);

        //Advanced Task
        List<Integer> array2 = new ArrayList<>();
        array2.add(1);
        array2.add(2);
        array2.add(3);
        Stream <Integer> arrayStream = array2.stream();
        array2.add(4);
        array2.add(5);
        array2.add(6);
        arrayStream.forEach(System.out::println);

    }
}
