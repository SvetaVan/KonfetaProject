package myStructures;

import java.util.*;

public class MyArrayList<T> extends MyAbstractList<T> {
    private Object[] array;
    private static final Object[] EMPTY_ARRAY = {};
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ARRAY_CAPACITY_MULTIPLIER = 2;
    int startingIndex;
    int endingIndex;



    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.array = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.array = EMPTY_ARRAY;
        } else {
            throw new IllegalArgumentException("Illegal argument was passed " + initialCapacity);

        }
    }

    public MyArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(T...toAdd){
        for (T element : toAdd) {
            add(element);
        }
    }

    public MyArrayList(int startingIndex, int endingIndex, Object[] array){
        this.array = array;
        this.startingIndex = startingIndex;
        this.endingIndex = endingIndex;



    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();//переделать на лист итератор
    }

    @Override
    public boolean add(T t) {
        // null elements are permitted
        if (this.size != 0 && array[0].getClass() != t.getClass()) {
            throw new ClassCastException("the type of passed element\n" +
                    " is incompatible with this list type\n" +
                    array[0].getClass() + " doesn't equal to " + t.getClass());
        }
        if (isEnoughCapacity()) {
            createArrayCopyWithIncreasedCapacity();
        }
        array[size] = t;
        ++size;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (indexOf(o) != -1) {
            removeElement(indexOf(o));
            return true;
        }
        return false;
    }

    @Override
    public T get(int i) {
        rangeCheck(i);
        return (T) array[i];
    }

    @Override
    public T set(int i, T t) {
        rangeCheck(i);
        Object elementToReturn = array[i];
        array[i] = t;
        return (T) elementToReturn;
    }

    @Override
    public void clear() {
        for (int i = 0; i <array.length ; i++) {
            array[i]=null;
        }
        size=0;
    }

    @Override
    public void add(int i, T t) {
        rangeCheckForAddAtSpecifiedPosition(i);//1. проверяем границы индекса
        if (i == this.size()) {//2. если добавляем в конец массива, то вызываем обычный метод add
            this.add(t);
        } else {//3. если добавляем в любое другое место массива
            if (array.length > this.size) {//если размеры достаточно
                addElementToArrayIfEnoughCapacity(i, t, array);
            } else {//если необходимо увеличить массив
                Object[] newArray = new Object[array.length * ARRAY_CAPACITY_MULTIPLIER];
                System.arraycopy(array, 0, newArray, 0, array.length);
                addElementToArrayIfEnoughCapacity(i, t, newArray);
                array = newArray;
            }
        }
    }

    @Override
    public T remove(int i) {
        rangeCheck(i);
        T elementToDelete = (T) array[i];
        removeElement(i);
        return elementToDelete;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new MyListIterator();
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        return new MyListIterator(i);
    }

    @Override
    //??? не поняла по поводу конструктора Переписать на использование View техники как мы делали с LinkedList-ом - сделать конструктор c startElement-ом
    public List<T> subList(int i, int i1) {
        if (i < 0 || i > this.size || i1 < 0 || i1 > this.size || i1 < i) {
            throw new IndexOutOfBoundsException("passed parameters are incorrect, the possible range is from 0 to " + (this.size()));
        }
        List<T> newArrayList = new MyArrayList<>(0);
        if (i == i1) {
            return newArrayList;
        }
        for (int j = i; j < i1; j++) {
            newArrayList.add((T) this.array[j]);
        }
        return Collections.unmodifiableList(newArrayList);
    }

    public void removeElement(int indexToRemove) {
        for (int i = indexToRemove; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    private void createArrayCopyWithIncreasedCapacity() {
        Object[] newArray;
        if(array.length==0){
            newArray = new Object[DEFAULT_CAPACITY];
        }else {
            newArray = new Object[array.length * ARRAY_CAPACITY_MULTIPLIER];
        }
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("index is not acceptable, the range should be from 0 to " + (this.size() - 1) + ", current index is " + index);
        }
    }

    public void rangeCheckForAddAtSpecifiedPosition(int index) {
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException("index is not acceptable, the range for element adding should be from 0 to " + (this.size()) + ", current index is " + index);
        }
    }

    private void addElementToArrayIfEnoughCapacity(int indexToInsert, T elementToInsert, Object[] array) {
        for (int j = this.size()+1; j > indexToInsert; j--) {
            array[j] = array[j - 1];
        }
        array[indexToInsert] = elementToInsert;
        size++;
    }

    private boolean isEnoughCapacity() {
        return array.length == 0 || array.length == this.size() + 1;
    }


    private class MyListIterator implements ListIterator<T> {
        int cursor;
        int lastReturned;

        private MyListIterator() {
            cursor = 0;
        }

        private MyListIterator(int startingPosition) {
            cursor = startingPosition;
        }

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            lastReturned = cursor;
            cursor++;
            return (T) array[lastReturned];
        }

        @Override
        public boolean hasPrevious() {
            int prev = cursor - 1;
            return prev >= 0;
        }

        @Override
        public T previous() {
            --cursor;
            if (cursor < 0) {
                throw new NoSuchElementException();
            }
            lastReturned = cursor;
            return (T) array[lastReturned];
        }

        @Override
        public int nextIndex() {
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            return cursor;
        }

        @Override
        public int previousIndex() {
            int prevIndex = cursor - 1;
            if (prevIndex < 0) {
                throw new NoSuchElementException("previousIndex can not be less than 0, prevIndex = " + prevIndex);
            }
            return prevIndex;
        }

        @Override
        public void remove() {
            if (cursor == 0) {
                throw new IndexOutOfBoundsException("Operation Removal was called before operation Next");
            }
            MyArrayList.this.remove(lastReturned);
            cursor--;
        }

        @Override
        public void set(T t) {
            MyArrayList.this.set(cursor, t);
        }

        @Override
        public void add(T t) {
            MyArrayList.this.add(cursor,t);
        }
    }


}



