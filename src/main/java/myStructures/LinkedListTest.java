package myStructures;

import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<Integer> integers=  new LinkedList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        ListIterator<Integer> listIterator = integers.listIterator();
        listIterator.add(10);
        listIterator.remove();
        System.out.println(integers);
    }
}
