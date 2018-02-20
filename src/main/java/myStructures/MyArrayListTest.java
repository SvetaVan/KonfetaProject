package myStructures;

import java.util.*;

public class MyArrayListTest {
    public static void main(String[] args) {
        MyArrayList<Integer> myArrayList = new MyArrayList<>(2);
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(11);
        List<Integer> collection = new LinkedList<>();
        collection.add(2);
        collection.add(11);
        List<Integer> collection2 = new LinkedList<>();
        collection2.add(2);
        collection2.add(10);
        collection2.add(10);
        collection2.add(10);

        System.out.println(myArrayList.removeAll(collection));
        for (int i = 0; i <myArrayList.size() ; i++) {
            System.out.println(myArrayList.get(i));
        }

    }
}
