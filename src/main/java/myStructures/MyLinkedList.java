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

    public MyLinkedList() {
    }

    private MyLinkedList(Node<T> root, Node<T> last, int size ){
        this.root = root;
        this.last = last;
        this.size = size;
    }

    @SafeVarargs
    public MyLinkedList(T... toAdd) {
        for (T element : toAdd) {
            add(element);
        }
    }


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
        while (currentElement != null) {
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

            destinationArray = (E[]) Array.newInstance(destinationArray.getClass().getComponentType(), this.size);
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
        if (t == null) {
            throw new NullPointerException("null elements are not permitted");
        }
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
        if (o == null) {
            throw new NullPointerException("the parameter for search is null");
        }
        if (root != null && o.getClass() != this.root.element.getClass()) {
            throw new ClassCastException("parameter type = " + o.getClass() + ", collection element type = " + this.root.element.getClass());
        }
        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext() && size != 0) {
            T currentObj = iterator.next();
            if (currentObj.equals(o)) {
                iterator.remove();
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("collection is null or doesn't contain elements");
        }
        if(collection.size()==0||this.size()==0){
            return false;
        }
        Iterator<?> iterator = collection.iterator();
        Object firstElement = iterator.next();
        if (root.element.getClass() != firstElement.getClass()) {
            throw new ClassCastException("the types of one or more elements\n" +
                    " in the specified collection are incompatible with this list\n" +
                    root.element.getClass() + " doesn't equal to " + firstElement.getClass());
        }
        if (!contains(firstElement)) {
            return false;
        }
        while (iterator.hasNext()) {
            if (!contains(iterator.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (T element : this) {
            stringBuilder.append(element);
            stringBuilder.append(", ");
        }
        if (stringBuilder.length() > 2) {
            stringBuilder.setLength(stringBuilder.length() - 2);
        }
        return "[" + stringBuilder + "]";
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (collection == null) {
            throw new NullPointerException("collection is null");
        }
        if (collection.size() == 0) {
            return true;
        }
        Iterator<? extends T> iterator = collection.iterator();
        Object firstElement = iterator.next();
        if (root.element.getClass() != firstElement.getClass()) {
            throw new ClassCastException("the types of one or more elements\n" +
                    " in the specified collection are incompatible with this list\n" +
                    root.element.getClass() + " doesn't equal to " + firstElement.getClass());
        }
        this.add((T) firstElement);
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
        if (i > collection.size() - 1 || i < 0) {
            throw new IndexOutOfBoundsException("i is out of bound, more than the size of collection or less than 0");
        }
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
            remove(iterator.next());
        }
        return true;
    }

    @Override
    //доделать
    public boolean retainAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("collection is null");
        }
        if (collection.size() == 0) {
            throw new NoSuchElementException("size of collection is 0");
        }
        Iterator<T> iterator = this.iterator();
        Object element = iterator.next();
        if (this.root.element.getClass() != element.getClass()) {
            throw new ClassCastException("the types of one or more elements\n" +
                    " in the specified collection are incompatible with this list\n" +
                    root.element.getClass() + " doesn't equal to " + element.getClass());
        }

        while (iterator.hasNext()) {
            if (!collection.contains(element)) {
                remove(element);
            }
            element = iterator.next();
        }
        return true;
    }

    @Override
    public void clear() {
        removeAll(this);
    }

    @Override
    public T get(int i) {
        Iterator<T> iterator = this.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            if (i == count) {
                return iterator.next();
            }
            iterator.next();
            count++;
        }
        throw new IndexOutOfBoundsException("передан некорректный параметр элемента, возможные значения от 0 до " + (this.size - 1));
    }


    /*@Override
    public void add(int i, T t) {
        Node<T> elementToShift=root;
        Node<T> elementToInsert = new Node<>();
        elementToInsert.element = t;
        int count = 0;
        while (elementToShift!=null){
            if(count==i&&count==0){
                elementToInsert.setPrevious(null);
                elementToInsert.setNext(elementToShift);
                elementToShift.setPrevious(elementToInsert);
                root = elementToInsert;
                return;
            }else if(count==i&&count==this.size-1){
                elementToInsert.setPrevious(elementToShift.getPrevious());
                elementToInsert.setNext(elementToShift);
                elementToShift.setNext(null);
                elementToInsert.getPrevious().setNext(elementToInsert);
                return;
            }else if(count==i){
                elementToInsert.setPrevious(elementToShift.getPrevious());
                elementToInsert.setNext(elementToShift);
                elementToShift.getPrevious().setNext(elementToInsert);
                elementToShift.setPrevious(elementToInsert);
                return;
            }
            elementToShift = elementToShift.next;
            count++;
        }
    }

*/

    private Node<T> findNode(int index) {
        Node<T> currentElement = root;
        if (index >= size) {
            throw new IndexOutOfBoundsException(String.format("Too big index %s. Maximum: %s", index, size - 1));
        }
        for (int i = 0; i < index; i++) {
            currentElement = currentElement.next;
        }
        return currentElement;
    }


    @Override
    public void add(int index, T element) {
        // Если нам передан индекс после последнего элемента, это значит что мы хотим добавить в конец
        if (index == size) {
            add(element);
            return;
        }

        Node<T> nextNode = findNode(index);
        //записываем предыдущий элемент
        Node<T> previousNode = nextNode.getPrevious();

        Node<T> newNode = new Node<>(null, null, element);

        // Связываем новый элемент со следующим
        newNode.setNext(nextNode);
        nextNode.setPrevious(newNode);

        // Cвязываем новый элемент с предыдущим, если такой существует (предыдуим может оказаться первый)
        if (previousNode != null) {
            previousNode.setNext(newNode);
            newNode.setPrevious(previousNode);
        }

        // Если мы добавляем в начало, не забудем и root обновить!
        if (index == 0) {
            root = newNode;
        }
        size++;
    }


    @Override
    public T remove(int i) {
        if (i > this.size || i < 0) {
            throw new IndexOutOfBoundsException("incorrect element index, index is: " + i + " size of the list is: " + this.size);
        }
        T deletedELement = this.get(i);
        this.remove(this.get(i));
        return deletedELement;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            throw new NullPointerException("Passed parameter is null");
        }
        if (this.root.element.getClass() != o.getClass()) {
            throw new ClassCastException("the class of passed parameter - " + o.getClass() + " is not equal to the class of the list element type " + this.root.element.getClass());
        }
        int count = 0;
        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            if (o.equals(iterator.next())) {
                return count;
            }
            count++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            throw new NullPointerException("Passed parameter is null");
        }
        if (this.root.element.getClass() != o.getClass()) {
            throw new ClassCastException("the class of passed parameter - " + o.getClass() + " is not equal to the class of the list element type " + this.root.element.getClass());
        }
        List<Integer> oIndexes = new ArrayList<>();
        int count = 0;
        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            if (o.equals(iterator.next())) {
                oIndexes.add(count);
            }
            count++;
        }
        return oIndexes.size() != 0 ? oIndexes.get(oIndexes.size() - 1) : -1;
    }

    @Override
    public T set(int i, T t) {
        if(t!=null){
        T replacedElement = this.get(i);
        this.findNode(i).setElement(t);
        return replacedElement;
        }
        else {
            throw new NullPointerException("the element to be placed is null");
        }
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
    //проверить корректность параметров
    public List<T> subList(int i, int i1) {

        return new MyLinkedList<>(findNode(i),findNode(i1-1),i1-i);
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

        void setElement(V element) {
            this.element = element;
        }


    }
}

