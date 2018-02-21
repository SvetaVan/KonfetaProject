package myStructures;

import java.util.*;

public class MyArrayList<T> extends MyAbstractList<T> {
    private Object[] array;
    private static final Object[] EMPTY_ARRAY = {};
    private static final Object[] DEFAULT_CAPACITY_EMPTY_ARRAY = {};
    private static final int DEFAULT_CAPACITY = 10;
    private static final int CREATE_ARRAY_MULTIPLIED_BY_2 = 2;


    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.array = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.array = EMPTY_ARRAY;
        } else {
            throw new IllegalArgumentException("Illegal argument was passed " + initialCapacity);
        }
    }

    public MyArrayList(){
        this.array = DEFAULT_CAPACITY_EMPTY_ARRAY;
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new InnerIterator();
    }

    @Override
    public T[] toArray() {
        return (T[]) array;
    }

    @Override //что??
    public <T1> T1[] toArray(T1[] t1s) {
        return null;
    }

    @Override
    public boolean add(T t) {
        // null elements are permitted
        //class cast exception???
        if (capacityCheckForAdd()) {
            arrayCopyIncreasedCapacity();
        }
        array[size] = t;
        ++size;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (indexOf(o) != -1) {
            arrayCopyToRemoveElement(indexOf(o));
            --size;
            return true;
        }
        return false;
    }

    @Override
    //class cast exception????
    public boolean containsAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("collection is null or doesn't contain elements");
        }
        if (collection.size() == 0 || this.size() == 0) {
            return false;
        }
        Iterator<?> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (!contains(iterator.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    //class cast exception???
    public boolean addAll(Collection<? extends T> collection) {
        if (collection == null) {
            throw new NullPointerException("collection is null or doesn't contain elements");
        }
        if (collection.size() == 0) {
            return true;
        }
        Iterator<? extends T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            this.add(iterator.next());
        }
        return true;
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        if (collection == null) {
            throw new NullPointerException("collection is null");
        }
        if (collection.size() == 0) {
            throw new NoSuchElementException("collection is empty");
        }
        this.rangeCheckForAdd(i);
        Iterator<? extends T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            this.add(i, iterator.next());
            i++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        Iterator<?> iterator = collection.iterator();
        while (iterator.hasNext()) {
            this.remove(iterator.next());
        }
        size = 0;
        return true;
    }

    @Override
    //size???
    public boolean retainAll(Collection<?> collection) {
        if (collection.size() == 0 || collection == null) {
            return false;
        }
        Object[] newArray = new Object[array.length];
        Iterator<?> collectionIterator = collection.iterator();
        while (collectionIterator.hasNext()) {
            int i = 0;
            Object nextElement = collectionIterator.next();
            if (this.contains(nextElement)) {
                newArray[i] = nextElement;
                i++;
            } else {
                size--;
            }
        }
        array = newArray;
        return true;
    }

    @Override
    public void clear() {
        removeAll(this);
        size = 0;
    }


    @Override
    public T get(int i) {
        rangeCheck(i);
        return (T) array[i];
    }

    @Override
    public T set(int i, T t) {
        rangeCheck(i);
        Object[] arrayNew = new Object[array.length];
        Object elementToReturn = array[i];
        for (int j = 0; j < arrayNew.length; j++) {
            if (j == i) {
                arrayNew[j] = t;
            } else {
                arrayNew[j] = array[j];
            }
        }
        array = arrayNew;
        return (T) elementToReturn;
    }

    private boolean capacityCheckForAdd() {
        if(array.length==0||array.length == this.size() + 1){
            return true;
        }
        return false;
    }

    private Object[] fillNewArrayForAdd(int indexToInsert, T elementToInsert, Object[] newArray) {
        int indexOld = 0, indexNew = 0;
        while (indexOld < this.size() && indexNew < newArray.length) {
            if (indexToInsert == indexOld) {
                newArray[indexNew] = elementToInsert;
                newArray[++indexNew] = array[indexOld];
            } else {
                newArray[indexNew] = array[indexOld];
            }
            ++indexNew;
            ++indexOld;
        }
        return newArray;
    }

    private Object[] arrayFactory() {
        Object[] newArray;
        if (capacityCheckForAdd()) {
            newArray = new Object[array.length * CREATE_ARRAY_MULTIPLIED_BY_2];
        } else {
            newArray = new Object[array.length + 1];
        }
        return newArray;
    }


    @Override
    public void add(int i, T t) {
        rangeCheckForAdd(i);//1. проверяем границы индекса

        if (i == this.size()) {//2. если добавляем в конец массива, то вызываем обычный метод add
            this.add(t);
        } else {//3. если добавляем в любое другое место массива
            array = fillNewArrayForAdd(i, t, arrayFactory());
            ++size;
        }
    }


    @Override
    public T remove(int i) {
        rangeCheck(i);
        T elementToDelete = (T) array[i];
        arrayCopyToRemoveElement(i);
        --size;
        return elementToDelete;
    }

    @Override
    public int indexOf(Object o) {
        if (array.length == 0) {
            System.out.println("Our collection is empty");
        }
        if (o == null) {
            throw new NullPointerException("Passed parameter is null");
        }
        if (array[0].getClass() != o.getClass()) {
            throw new ClassCastException("the class of passed parameter - " + o.getClass() + " is not equal to the class of the list element type " + array[0].getClass());
        }
        int count = 0;
        for (Object o1 : array) {
            if (o.equals(o1)) {
                return count;
            }
            count++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (array.length == 0) {
            System.out.println("Our collection is empty");
        }
        if (o == null) {
            throw new NullPointerException("Passed parameter is null");
        }
        if (array[0].getClass() != o.getClass()) {
            throw new ClassCastException("the class of passed parameter - " + o.getClass() + " is not equal to the class of the list element type " + array[0].getClass());
        }
        for (int i = array.length - 1; i == 0; i--) {
            if (o.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        return null;
    }

    @Override
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
        return newArrayList;
    }


    private void arrayCopyIncreasedCapacity() {
        Object[] arrayIncreased;
        if(array.length==0){
            arrayIncreased = new Object[DEFAULT_CAPACITY];
        }else {
            arrayIncreased = new Object[array.length * CREATE_ARRAY_MULTIPLIED_BY_2];
        }
        for (int i = 0; i < array.length; i++) {
            arrayIncreased[i] = array[i];
        }
        array = arrayIncreased;
    }

    private void arrayCopyToRemoveElement(int indexToRemove) {
        Object[] arrayNew = new Object[array.length - 1];
        int indexArrayOld = 0, indexArrayNew = 0;
        while (indexArrayOld < array.length && indexArrayNew < arrayNew.length) {
            if (indexArrayOld != indexToRemove) {
                arrayNew[indexArrayNew] = array[indexArrayOld];
                indexArrayNew++;//увеличиваем индекс нового массива только если элемент не должен быть удален у первоначального массива
            }
            indexArrayOld++;//индекс в первоначальном массиве увеличиваем в любом случае
        }
        array = arrayNew;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("index is not acceptable, the range should be from 0 to " + (this.size() - 1) + ", current index is " + index);
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException("index is not acceptable, the range for element adding should be from 0 to " + (this.size()) + ", current index is " + index);
        }
    }


    private class InnerIterator implements Iterator<T> {
        int cursor;
        int lastReturned;

        private InnerIterator() {
            cursor = 0;
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
        public void remove() {
            if(cursor==0){
                throw new IndexOutOfBoundsException("Operation Removal was called before operation Next");
            }
            arrayCopyToRemoveElement(lastReturned);
            --size;
            --cursor;
        }
    }
}



