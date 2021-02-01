package gof.behavioral;

import lombok.extern.java.Log;

@Log
public class IteratorPattern {
    public static void main(String[] args) {
        ArrayContainer arrayContainer = new ArrayContainer();
        Iterator iterator = arrayContainer.getIterator();
        while (iterator.hasNext()){
            log.info((String) iterator.next());
        }
    }
}

interface Iterator{
    boolean hasNext();
    Object next();
}

interface Container{
    Iterator getIterator();
}

class ArrayContainer implements Container {
    String[] array = {"1","2","3","4"};

    @Override
    public Iterator getIterator() {
        return new ArrayIterator();
    }

class ArrayIterator implements Iterator {

    int index;
    @Override
    public boolean hasNext() {
        return (index<array.length);
    }

    @Override
    public Object next() {
        if(hasNext()){
            return array[index++];
        }
        return null;
    }
}
}
