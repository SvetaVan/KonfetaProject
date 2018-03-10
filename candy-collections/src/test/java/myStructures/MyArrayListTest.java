package myStructures;

import java.util.List;

public class MyArrayListTest extends MyListTest {
    @Override
    public <T> List<T> createList(T... args) {
        return new MyArrayList<>(args);
    }
}
