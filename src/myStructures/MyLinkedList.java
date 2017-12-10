package myStructures;

import java.lang.reflect.Array;
import java.util.*;

public class MyLinkedList<T> implements List<T> {
    Node<T> root;
    private Node<T> last;
    private int size;

    /*@Override
    public int size() {
        int size = 1;
        if(this.root==null){
            return 0;
        }
        Node<T> currentElement = this.root;
        while (currentElement.next!=null){
            size++;
            currentElement = currentElement.next;
        }
        return size;
    }*/


    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node<T> currentElement = this.root;
        while (currentElement.next != null) {
            if (currentElement.element.equals(o)) {
                return true;
            }
            currentElement = currentElement.next;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator<T>(this);
    }

    @Override
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

    @Override
    public <E> E[] toArray(E[] destinationArray) {
        if (destinationArray == null) {
            throw new NullPointerException("the parameter source array is null");
        }

        if (size > 0) {
            Class<?> arrayClass = destinationArray.getClass().getComponentType();
            Class<?> elementsClass = root.element.getClass();
            if (!arrayClass.isAssignableFrom(elementsClass)) {
                throw new ArrayStoreException("if the runtime type of the specified array is not a supertype of the runtime type of every element in this list");
            }
        }

        if (destinationArray.length == this.size) {
            Iterator<T> iterator = this.iterator();
            int i = 0;
            while (iterator.hasNext()) {
                T currentElement = iterator.next();
                destinationArray[i] = (E) currentElement;
                i++;
            }
            return destinationArray;
        } else if (destinationArray.length > this.size) {
            Iterator<T> iterator = this.iterator();
            int i = 0;
            while (iterator.hasNext()) {
                T currentElement = iterator.next();
                destinationArray[i] = (E) currentElement;
                i++;
            }
            for (int j = destinationArray.length - 1; j > this.size; j--) {
                destinationArray[j] = null;
            }
            return destinationArray;
        } else { //if (destinationArray.length < this.size) {

            destinationArray = (E[]) Array.newInstance(destinationArray.getClass().getComponentType(),this.size);
            Iterator<T> iterator = this.iterator();
            int i = 0;
            while (iterator.hasNext()) {
                T currentElement = iterator.next();
                destinationArray[i] = (E) currentElement;
                i++;
            }

            return destinationArray;
    }
    }



    @Override
    public boolean add(T t) {
        size++;
        if (root == null) {
            root = new Node<T>(null, null, t);
            last = root;
            return true;
        } else {
            Node<T> newNode = new Node<>(last, null, t);
            last.next = newNode;
            last = newNode;
            return true;
        }
    }

    public Node<T> returnLastElement(Node<T> node) {
        if (node == null) {
            return null;
        }
        while (node.next != null) {
            node = node.next;
        }
        return node;
    }

    @Override
    public boolean remove(Object o) {
        if(o==null){
            throw new NullPointerException("the parameter for search is null");
        }
        if(root!=null&&o.getClass()!=this.root.element.getClass()){
            throw new ClassCastException("parameter type = "+o.getClass()+", collection element type = " + this.root.element.getClass());
        }
        Iterator<T> iterator = this.iterator();
        while(iterator.hasNext()){
            T currentObj = iterator.next();
            if(currentObj.equals(o)){
                iterator.remove();

                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return false;
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int i) {
        return null;
    }

    @Override
    public T set(int i, T t) {
        return null;
    }

    @Override
    public void add(int i, T t) {

    }

    @Override
    public T remove(int i) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
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


    static class Node<V> {
        private Node<V> previous;
        private Node<V> next;
        private V element;

        private Node() {
        }

        Node(Node<V> previous, Node<V> next, V element) {
            this.previous = previous;
            this.next = next;
            this.element = element;
        }

        @Override
        public boolean equals(Object otherObj) {
            if (this == otherObj) {
                return true;
            }
            if (otherObj == null) {
                return false;
            }
            if (this.getClass() != otherObj.getClass()) {
                return false;
            }
            Node<V> other = (Node<V>) otherObj;
            return this.element.equals(other.element);
        }

        @Override
        public int hashCode() {
            return element.hashCode();
        }

        Node<V> getNext() {
            return next;
        }

        Node<V> getPrevious() {
            return previous;
        }


        V getCurrentElement() {
            return element;
        }

        void setPrevious(Node<V> node) {
            this.previous = node;
        }

        void setNext(Node<V> node) {
            this.next = node;
        }


    }


}
