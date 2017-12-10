package myStructures;

import java.util.Iterator;

public class TestIterator {
    public static void main(String[] args) {
    testToArrayWithParameter_destSizeIsLess();


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
