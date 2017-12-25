package myStructures;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TestIterator {
    public static void main(String[] args) {
    testClear();

    }

    public static void testClear() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(3);
        myLinkedList.add(2);
        myLinkedList.clear();
        Iterator<Integer> integerIterator = myLinkedList.iterator();
        while (integerIterator.hasNext()){
            System.out.println(integerIterator.next());
        }
        System.out.println("final size = "+ myLinkedList.size());

    }

    public static void testRetainAll() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(3);
        myLinkedList.add(2);
        Set<Integer> mySet = new HashSet<>();
        mySet.add(2);
        myLinkedList.retainAll(mySet);

        Iterator<Integer> integerIterator = myLinkedList.iterator();
        while (integerIterator.hasNext()){
            System.out.println(integerIterator.next());
        }
        System.out.println("final size = "+ myLinkedList.size());

    }

    public static void testRemoveAll() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(3);
        myLinkedList.add(2);
        Set<Integer> mySet = new HashSet<>();
        mySet.add(3);
        myLinkedList.removeAll(mySet);
        Iterator<Integer> integerIterator = myLinkedList.iterator();
        while (integerIterator.hasNext()){
            System.out.println(integerIterator.next());
        }
        System.out.println("final size = "+ myLinkedList.size());

    }

    public static void testAddAllAtPosition() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        Set<Integer> mySet = new HashSet<>();
        mySet.add(5);
        mySet.add(9);
        mySet.add(11);
        myLinkedList.addAll(3,mySet);
        Iterator<Integer> integerIterator = myLinkedList.iterator();
        while (integerIterator.hasNext()){
            System.out.println(integerIterator.next());
        }
        System.out.println("final size = "+ myLinkedList.size());

    }

    public static void testAddAtPosition() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        myLinkedList.add(1,10);
        Iterator<Integer> integerIterator = myLinkedList.iterator();
          while (integerIterator.hasNext()){
              System.out.println(integerIterator.next());
        }

    }


    public static void testAddAll() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        Set<Integer> mySet = new HashSet<>();
        System.out.println(myLinkedList.addAll(mySet));
    }

    public static void testContainsAll() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        Set<Integer> mySet = new HashSet<>();
        mySet.add(1);
        mySet.add(2);
        mySet.add(3);
        System.out.println(myLinkedList.containsAll(mySet));
    }



    public static void testGetElemnt() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        System.out.println("возвращаем 0 элемент");
        System.out.println(myLinkedList.get(0)==1?"true":"false");
        System.out.println("возвращаем 1 элемент");
        System.out.println(myLinkedList.get(1)==2?"true":"false");
        System.out.println("возвращаем 2 элемент");
        System.out.println(myLinkedList.get(2)==3?"true":"false");
        System.out.println("элемент выходит за пределы массива, больше длины массива");
        System.out.println(myLinkedList.get(5));
        System.out.println("элемент выходит за пределы массива, меньше длины массива");
        System.out.println(myLinkedList.get(-5));

    }

    public static void testIterator() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        Iterator<Integer> integerIterator = myLinkedList.iterator();
        while (integerIterator.hasNext()) {
            int currentElement = integerIterator.next();
            System.out.print(currentElement);
            if (currentElement == 2) {
                integerIterator.remove();
                System.out.println(" element deleted ");
            } else {
                System.out.println(" element wasn't deleted ");
            }
        }
    }

    public static void testToArray() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);

        Object[] objects = myLinkedList.toArray();
        for (Object obj : objects) {
            System.out.println(obj);
        }
    }

    public static void testToArrayWithParameter_Null() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        Integer[] destinationArraay = null;
        System.out.println("test of destination array equals null");
        myLinkedList.toArray(destinationArraay);

    }

    public static void testToArrayWithParameter_StoreException() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        String[] destinationArraay = new String[3];
        System.out.println("test of store exception");
        myLinkedList.toArray(destinationArraay);

    }

    public static void testToArrayWithParameter_equalCases() {
        System.out.println("sizes are the same");
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        Integer[] destinationArray = new Integer[3];
        myLinkedList.toArray(destinationArray);
        for (Integer integer:destinationArray) {
            System.out.println(integer);
        }
    }

    public static void testToArrayWithParameter_destSizeIsHigher() {
        System.out.println("destination array size is higher");
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        Integer[] destinationArray = new Integer[5];
        myLinkedList.toArray(destinationArray);
        for (Integer integer:destinationArray) {
            System.out.println(integer);
        }
    }

    public static void testToArrayWithParameter_destSizeIsLess() {
        System.out.println("destination array size is less");
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        Integer[] destinationArray = new Integer[1];
        Integer[] array = myLinkedList.<Integer>toArray(destinationArray);
        for (Object integer:array) {
            System.out.println(integer);
        }
    }


}
