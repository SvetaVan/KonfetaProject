package myStructures;

import java.util.Iterator;

public class TestIterator {
    public static void main(String[] args) {
    MyLinkedList <Integer> myLinkedList = new MyLinkedList<>();
    myLinkedList.add(1);
    myLinkedList.add(2);
    myLinkedList.add(3);
    Iterator<Integer> integerIterator = myLinkedList.iterator();
    while(integerIterator.hasNext()){
        int currentElement = integerIterator.next();
        System.out.print(currentElement);
        if(currentElement==2){
            integerIterator.remove();
            System.out.println(" element deleted ");
        }else {
            System.out.println(" element wasn't deleted ");
        }
    }
    }
}
