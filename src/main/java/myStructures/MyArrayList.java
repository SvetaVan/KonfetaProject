package myStructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList<T> extends MyAbstractList<T> {
    private Object[] array;
    private static final Object[] EMPTY_ARRAY = {};
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
        return null;
    }

    @Override
    public T[] toArray() {
        return (T[]) array;
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        return null;
    }

    @Override
    public boolean add(T t) {
        // null elements are permitted
        //class cast exception???
        if (array.length == this.size()) {
            array = arrayCopyIncreasedCapacity(array);
        }
        if (size > 0) {
            array[size] = t;
        } else {
            array[0] = t;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (indexOf(o) != -1) {
            array = arrayCopyToRemoveElement(array, indexOf(o));
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
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        Iterator<?> iterator = collection.iterator();
        while (iterator.hasNext()){
            this.remove(iterator.next());
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {
        removeAll(this);
    }


    // size or length
    @Override
    public T get(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException("передан некорректный параметр элемента, возможные значения от 0 до " + (this.size - 1));
        }

        return (T) array[i];
    }

    @Override
    public T set(int i, T t) {
        return null;
    }

    @Override
    //пока не работает
    public void add(int i, T t) {
        //если добавляем в конец, то вызываем обычный метод add
        if (i == this.size()) {
            this.add(t);
        }

        //если добавляем в любое место массива

        if (array.length == this.size()) {
            array = arrayCopyIncreasedCapacity(array);
        }


    }

    @Override
    public T remove(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException("Acceptable index range is from 0 to " + this.size() + ", passed index is " + i);
        }
        T elementToDelete = (T) array[i];
        array = arrayCopyToRemoveElement(array, i);
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
        return null;
    }


    //ниже (3 метода) буду переделывать, какая-то хрень получилась
    public static Object[] multiplyCapacity(Object[] array) {
        Object[] arrayMultipliedCapacity = new Object[array.length * CREATE_ARRAY_MULTIPLIED_BY_2];
        return arrayMultipliedCapacity;
    }


    public static Object[] arrayCopyIncreasedCapacity(Object[] arrayToCopy) {
        Object[] arrayIncreased = multiplyCapacity(arrayToCopy);
        for (int i = 0; i < arrayToCopy.length; i++) {
            arrayIncreased[i] = arrayToCopy[i];
        }
        return arrayIncreased;
    }

    public static Object[] arrayCopyToRemoveElement(Object[] arrayToCopy, int indexToRemove) {
        Object[] arrayNew = new Object[arrayToCopy.length - 1];
        int indexArray2Copy = 0, indexArrayNew = 0;
        while (indexArray2Copy < arrayToCopy.length && indexArrayNew < arrayNew.length) {
            if (indexArray2Copy != indexToRemove) {
                arrayNew[indexArrayNew] = arrayToCopy[indexArray2Copy];
                indexArrayNew++;//увеличиваем индекс нового массива только если элемент не должен быть удален у первоначального массива
            }
            indexArray2Copy++;//индекс в первоначальном массиве увеличиваем в любом случае
        }
        return arrayNew;
    }


}
