package collections.map;

import lombok.extern.java.Log;

@Log
public class HashMapExample {
    public static void main(String[] args) {
        MyHashMap hashMap = new MyHashMap();
        hashMap.put(new Key(1), new Value(987));
        hashMap.put(new Key(2), new Value(634));
        hashMap.put(new Key(3), new Value(234));
        hashMap.put(new Key(4), new Value(425));

        log.info(String.valueOf(hashMap.containsKey(new Key(2))));
        log.info(String.valueOf(hashMap.get(new Key(4)).getValue()));
    }
}
