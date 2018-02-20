package myStructures;

import java.util.List;

public abstract class MyAbstractList<T> implements List<T> {
    protected int size;

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size==0;
    }

}
