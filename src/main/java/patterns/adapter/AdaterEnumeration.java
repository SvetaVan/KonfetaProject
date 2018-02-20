package patterns.adapter;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class AdaterEnumeration<T> implements Enumeration<T> {
    Iterator<T> iterator;

    public AdaterEnumeration(Iterator<T> iterator){
        this.iterator = iterator;
    }

    @Override
    public boolean hasMoreElements() {
        return iterator.hasNext();
    }

    @Override
    public T nextElement() {
        return iterator.next();
    }


    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(0);
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);
        Enumeration<Integer> integerEnumeration = new AdaterEnumeration<>(integerList.iterator());


        //AdaterEnumeration adaterIIterator = new AdaterEnumeration(integerEnumeration);

    }
}
