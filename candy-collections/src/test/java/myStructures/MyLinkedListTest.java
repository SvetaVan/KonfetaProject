package myStructures;

import java.util.List;

public class MyLinkedListTest extends MyListTest{
    @Override
    public <T> List<T> createList(T... args) {
        return new MyLinkedList<>(args);
    }



}
