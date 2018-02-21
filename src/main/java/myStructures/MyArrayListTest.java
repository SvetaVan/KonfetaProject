package myStructures;

import java.util.*;

public class MyArrayListTest {
    public static void main(String[] args) {
        MyArrayList<Integer> myArrayList = new MyArrayList<>(2);
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);

        Iterator <Integer> integerIterator = myArrayList.iterator();

        integerIterator.next();
        for (int i = 0; i <myArrayList.size() ; i++) {
            System.out.println(myArrayList.get(i));
        }
        System.out.println(myArrayList.size());





        /*ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        Iterator<Integer> integerIteratorAR = arrayList.iterator();
        integerIteratorAR.next();
        integerIteratorAR.remove();
        for (Integer integer:arrayList) {
            System.out.println(integer);
        }
        System.out.println(integerIteratorAR.next());*/




    }
}
