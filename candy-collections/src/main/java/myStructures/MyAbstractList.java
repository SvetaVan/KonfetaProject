package myStructures;

import com.google.common.base.Preconditions;

import java.lang.reflect.Array;
import java.util.*;

public abstract class MyAbstractList<T> implements List<T> {
    protected int size;

    //tested
    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    //tested
    public int indexOf(Object o) {
        Preconditions.checkNotNull(o, "Passed parameter is null");
        classCastParameterException(o);
        Iterator<T> collectionIterator = this.iterator();
        int count = 0;
        while (collectionIterator.hasNext()) {
            if (o.equals(collectionIterator.next())) {
                return count;
            }
            count++;
        }
        return -1;
    }

    //tested
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    //tested
    public boolean removeAll(Collection<?> collection) {
        Preconditions.checkNotNull(collection, "Passed parameter is null");
        classCastCollectionException(collection);
        boolean modified = false;
        for (Object element : collection) {
            remove(element);
            modified = true;
        }
        return modified;
    }

    //tested
    public boolean containsAll(Collection<?> collection) {
        Preconditions.checkNotNull(collection, "Коллекция должна быть передана");
        if (collection.size() == 0 || this.size() == 0) {
            return false;
        }
        classCastCollectionException(collection);
        Iterator<?> iterator = collection.iterator();//итератор для переданной коллекции
        while (iterator.hasNext()) {
            if (!contains(iterator.next())) {
                return false;
            }
        }
        return true;
    }

    //tested
    public boolean addAll(Collection<? extends T> collection) {
        npeException(collection);
        if (collection.size() == 0) {
            return true;
        }
        classCastCollectionException(collection);
        boolean modified = false;
        Iterator<? extends T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            this.add(iterator.next());
            modified = true;
        }
        return modified;
    }

    //tested
    public boolean addAll(int i, Collection<? extends T> collection) {
        npeException(collection);
        nseException(collection);
        if (i > collection.size() || i < 0) {
            throw new IndexOutOfBoundsException("i is out of bound, more than the size of collection or less than 0");
        }
        Iterator<? extends T> iterator = collection.iterator();
        boolean modified = false;
        while (iterator.hasNext()) {
            this.add(i, iterator.next());
            i++;
            modified = true;
        }
        return modified;
    }

    //tested
    public boolean retainAll(Collection<?> collection) {
        npeException(collection);
        classCastCollectionException(collection);

        Iterator<T> listIterator = this.iterator();
        T listElement;
        boolean modified = false;
        while (listIterator.hasNext()) {
            listElement = listIterator.next();
            if (!collection.contains(listElement)) {
                remove(listElement);
                modified = true;
            }
        }
        return modified;
    }

    //tested
    public Object[] toArray() {
        Object[] objectsArray = new Object[this.size];
        Iterator<T> iterator = this.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            T currentElement = iterator.next();
            objectsArray[i] = currentElement;
            i++;
        }
        return objectsArray;
    }

    //tested
    public <E> E[] toArray(E[] destinationArray) {
        if (destinationArray == null) {
            throw new NullPointerException("the parameter source array is null");
        }
        Iterator<T> iteratorForClassCheck = this.iterator();
        if (size > 0) {
            Class<?> arrayClass = destinationArray.getClass().getComponentType();
            Class<?> elementsClass = iteratorForClassCheck.next().getClass();
            if (!arrayClass.isAssignableFrom(elementsClass)) {
                throw new ArrayStoreException("if the runtime type of the specified array is not a supertype of the runtime type of every element in this list");
            }
        }

        if (destinationArray.length == this.size) {
            fillArray(destinationArray);
        } else if (destinationArray.length > this.size) {
            destinationArray = fillArray(destinationArray);
            for (int j = destinationArray.length - 1; j > this.size; j--) {
                destinationArray[j] = null;
            }
            return destinationArray;
        } else { //if (destinationArray.length < this.size) {
            destinationArray = (E[]) Array.newInstance(destinationArray.getClass().getComponentType(), this.size);
            fillArray(destinationArray);
        }
        return destinationArray;
    }

    //This method is used for method toArray
    private <E> E[] fillArray(E[] destinationArray) {
        Iterator<T> iterator = this.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            T currentElement = iterator.next();
            destinationArray[i] = (E) currentElement;
            i++;
        }
        return destinationArray;
    }

    //tested
    @Override
    public int lastIndexOf(Object o) {
        if (this.size() == 0) {
            System.out.println("Collection is empty");
        }
        Preconditions.checkNotNull(o, "Passed parameter is null");
        classCastParameterException(o);

        ListIterator<T> iterator = this.listIterator(this.size());
        T prevElement = iterator.previous();
        int lastIndex = this.size() - 1;
        while (iterator.hasPrevious()) {
            if (o.equals(prevElement)) {
                return lastIndex;
            }
            prevElement = iterator.previous();
            lastIndex--;
        }
        return -1;
    }


    //exceptions

    public void npeException(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Collection is null or doesn't contain elements");
        }
    }

    public void nseException(Collection<?> collection) {
        if (collection.size() == 0) {
            throw new NoSuchElementException("size of collection is 0");
        }
    }

    public void classCastCollectionException(Collection<?> collection) {
        if (this.size() > 0 && collection.size() > 0) {
            Iterator<?> collectionIterator = collection.iterator();//итератор для переданной коллекции
            Object collectionFirstElement = collectionIterator.next();

            Iterator<T> myAbstractListIterator = this.iterator();//итератор для листа
            T absListFirstElement = myAbstractListIterator.next();

            if (absListFirstElement.getClass() != collectionFirstElement.getClass()) {
                throw new ClassCastException("the types of one or more elements\n" +
                        " in the specified collection are incompatible with this list\n" +
                        absListFirstElement.getClass() + " doesn't equal to " + collectionFirstElement.getClass());
            }
        }
    }

    public void classCastParameterException(Object o) {
        if (this.size() > 0) {
            Iterator<T> iterator = this.iterator();
            T rootElement = iterator.next();//получаем первый элемент для сравнения классов
            if (rootElement.getClass() != o.getClass()) {
                throw new ClassCastException("the class of passed parameter - " + o.getClass() + " is not equal to the " +
                        "class of the list element type " + rootElement.getClass());
            }
        }
    }
}


