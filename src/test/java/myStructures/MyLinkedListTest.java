package myStructures;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import java.util.*;

public class MyLinkedListTest {

    @Test
    public void containsAllTest() {
        MyLinkedList<Integer> integers = new MyLinkedList<>(1, 2, 3);
        {
            MyLinkedList<Integer> integersToCheck = new MyLinkedList<>(1, 2);
            Assert.assertTrue("Коллекция " + integers.toString() + " не содержит все элементы коллекции " + integersToCheck.toString(), integers.containsAll(integersToCheck));
        }

        {
            MyLinkedList<Integer> integersToCheck = new MyLinkedList<>();
            Assert.assertFalse("Коллекция " + integers.toString() + " содержит элементы коллекции " + integersToCheck.toString(), integers.containsAll(integersToCheck));
        }

        {
            MyLinkedList<Integer> integers1 = new MyLinkedList<>();
            MyLinkedList<Integer> integersToCheck = new MyLinkedList<>(1, 2);
            Assert.assertFalse("Коллекция " + integers1.toString() + "  содержит элементы коллекции " + integersToCheck.toString(), integers1.containsAll(integersToCheck));
        }
    }

    @Test(expected = ClassCastException.class)
    public void containsAllClassCastExceptionTest() {
        MyLinkedList<Integer> integers = new MyLinkedList<>(1, 2, 3);
        MyLinkedList<String> integersToCheck = new MyLinkedList<>("one", "two");
        integers.containsAll(integersToCheck);
    }

    @Test
    public void getElementTest() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>(1, 2, 3);
        Assert.assertEquals("получен некорректный первый (0) элемент, ожидаемое значение 1, фактическое " + myLinkedList.get(0), (Integer) 1, myLinkedList.get(0));
        Assert.assertEquals("получен некорректный элемент в середине коллекции, ожидаемое значение 2, фактическое " + myLinkedList.get(1), (Integer) 2, myLinkedList.get(1));
        Assert.assertEquals("получен некорректный последний элемент, ожидаемое значение 3, фактическое " + myLinkedList.get(2), (Integer) 3, myLinkedList.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getElementIndexOutOfBoundException() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>(1, 2, 3);
        myLinkedList.get(5);
    }


    @Test
    public void addAllTest() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>(1, 2, 3);
        {
            Set<Integer> mySet = new HashSet<>();
            mySet.add(5);
            mySet.add(10);
            Assert.assertTrue("элементы коллекции " + mySet.toString() + " не были добавлены либо некорректно добавлены в коллекцию (после изменения) " + myLinkedList.toString(), myLinkedList.addAll(mySet));
        }

        {
            Set<Integer> mySet = new HashSet<>();
            Assert.assertTrue("элементы коллекции " + mySet.toString() + "  были успешно добавлены, результат добавления в коллекцию (после изменения) " + myLinkedList.toString(), myLinkedList.addAll(mySet));
        }
    }

    @Test
    public void testSet() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>(1, 2, 3);
        myLinkedList.set(0, 5);
        Assert.assertEquals("утанавливаем первый элемент в значение 5", (Integer) 5, myLinkedList.get(0));
        myLinkedList.set(1, 5);
        Assert.assertEquals("утанавливаем элемент в середине в значение 5", (Integer) 5, myLinkedList.get(1));
        myLinkedList.set(2, 5);
        Assert.assertEquals("утанавливаем последний элемент в значение 5", (Integer) 5, myLinkedList.get(2));
    }

    @Test(expected = NullPointerException.class)
    public void setNPETest() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>(1, 2, 3);
        myLinkedList.set(0, null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setIndexOutOfBoundTest() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>(1, 2, 3);
        myLinkedList.set(9, 2);
    }

    @Test
    public void testIterator() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>(1, 2, 3);
        Iterator<Integer> integerIterator = myLinkedList.iterator();
        Assert.assertTrue("некорректно предоставляет информацию о следующем элементе", integerIterator.hasNext());
        int currentElement = integerIterator.next();
        Assert.assertEquals("некорректно возвращен следующий элемент, должен быть 1, по факту " + currentElement, 1, currentElement);

        Assert.assertTrue("некорректно предоставляет информацию о следующем элементе", integerIterator.hasNext());
        currentElement = integerIterator.next();
        Assert.assertEquals("некорректно возвращен следующий элемент, должен быть 2, по факту " + currentElement, 2, currentElement);

        Assert.assertTrue("некорректно предоставляет информацию о следующем элементе", integerIterator.hasNext());
        currentElement = integerIterator.next();
        Assert.assertEquals("некорректно возвращен следующий элемент, должен быть 3, по факту " + currentElement, 3, currentElement);

        Assert.assertFalse("некорректно предоставляет информацию о следующем элементе, коллекция не имеет слудующиего элемента", integerIterator.hasNext());

        integerIterator.remove();
        Assert.assertTrue("последний элемент (равный 3) не был удален", !myLinkedList.contains(3));

    }

    @Test
    public void sublistTest() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>(1, 2, 3, 24);
        List<Integer> sublist = myLinkedList.subList(1, 4);

        Assert.assertEquals("размер", 3, sublist.size());
        Assert.assertEquals("первый элемент", (Integer) 2, sublist.get(0));
        Assert.assertEquals("второй элемент", (Integer) 3, sublist.get(1));
        Assert.assertEquals("третий элемент", (Integer) 24, sublist.get(2));
        Iterator<Integer> iterator = sublist.iterator();
        Assert.assertTrue("некорректно предоставляет информацию о следующем элементе", iterator.hasNext());
        int currentElement = iterator.next();
        Assert.assertEquals("некорректно возвращен следующий элемент, должен быть 2, по факту " + currentElement, 2, currentElement);

        Assert.assertTrue("некорректно предоставляет информацию о следующем элементе", iterator.hasNext());
        currentElement = iterator.next();
        Assert.assertEquals("некорректно возвращен следующий элемент, должен быть 3, по факту " + currentElement, 3, currentElement);

        Assert.assertTrue("некорректно предоставляет информацию о следующем элементе", iterator.hasNext());
        currentElement = iterator.next();
        Assert.assertEquals("некорректно возвращен следующий элемент, должен быть 24, по факту " + currentElement, 24, currentElement);

        Assert.assertFalse("коллекция не имеет слудующиего элемента", iterator.hasNext());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSublistAdd() {
        List<String> testList = new MyLinkedList<>("echo", "hello").subList(0, 1);
        testList.add("mama");
    }

    @Test
    public void testListIterator() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>(1, 2, 3, 4);
        ListIterator<Integer> listIterator = myLinkedList.listIterator();
        Assert.assertTrue("лист итератор при первом вызове hasNext (элементы в коллекции есть) некорректно отрабатывает, возвращая ложь", listIterator.hasNext());
        Assert.assertEquals("incorrect 1 element", (Integer) 1, listIterator.next());
        Assert.assertEquals("incorrect next index", 1, listIterator.nextIndex());

        Assert.assertTrue("лист итератор при первом вызове hasNext (элементы в коллекции есть) некорректно отрабатывает, возвращая ложь", listIterator.hasNext());
        Assert.assertEquals("incorrect 1 element", (Integer) 2, listIterator.next());
        Assert.assertEquals("incorrect next index", 2, listIterator.nextIndex());

        Assert.assertTrue("лист итератор некорректно отрабатывает, возвращая ложь", listIterator.hasNext());
        Assert.assertEquals("incorrect 1 element", (Integer) 3, listIterator.next());
        Assert.assertEquals("incorrect next index", 3, listIterator.nextIndex());

        Assert.assertTrue("лист итератор некорректно отрабатывает, возвращая ложь", listIterator.hasNext());
        Assert.assertEquals("incorrect 1 element", (Integer) 4, listIterator.next());
        Assert.assertEquals("incorrect next index", 3, listIterator.nextIndex());

        Assert.assertTrue("лист итератор при первом вызове hasPrevious некорректно отрабатывает, возвращая ложь", listIterator.hasPrevious());
        Assert.assertEquals("incorrect 1 element", (Integer) 3, listIterator.previous());
        Assert.assertEquals("incorrect next index", 1, listIterator.previousIndex());

        Assert.assertTrue("лист итератор при вызове hasPrevious некорректно отрабатывает, возвращая ложь", listIterator.hasPrevious());
        Assert.assertEquals("incorrect 1 element", (Integer) 2, listIterator.previous());
        Assert.assertEquals("incorrect next index", 0, listIterator.previousIndex());

        Assert.assertTrue("лист итератор при вызове hasPrevious некорректно отрабатывает, возвращая ложь", listIterator.hasPrevious());
        Assert.assertEquals("incorrect 1 element", (Integer) 1, listIterator.previous());
        Assert.assertEquals("incorrect next index", -1, listIterator.previousIndex());

    }

    @Test
    public void testAddNext() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>(1, 2, 3, 4);
        ListIterator<Integer> listIterator = myLinkedList.listIterator();
        //вызываем next
        Assert.assertEquals((Integer)1,listIterator.next());
        //добавляем элемент
        listIterator.add(10);
        Assert.assertEquals((Integer)10, myLinkedList.get(1));
        Assert.assertEquals(5, myLinkedList.size());
        listIterator.next();
        listIterator.next();
        Assert.assertEquals((Integer)4,listIterator.next());
        listIterator.add(10);
        Assert.assertEquals((Integer)10, myLinkedList.get(5));
        listIterator.add(10);
        Assert.assertEquals((Integer)10, myLinkedList.get(6));

    }

    @Test
    public void testAddPrevious() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>(1, 2, 3, 4);
        ListIterator<Integer> listIterator = myLinkedList.listIterator();
        //вызываем next
        Assert.assertEquals((Integer) 1, listIterator.next());
        //добавляем элемент
        listIterator.add(10);
        Assert.assertEquals((Integer) 10, myLinkedList.get(1));
        Assert.assertEquals(5, myLinkedList.size());
        listIterator.next();
        listIterator.next();
        Assert.assertEquals((Integer) 4, listIterator.next());
        Assert.assertEquals((Integer) 4, listIterator.previous());
        Assert.assertEquals((Integer) 3, listIterator.previous());
        Assert.assertEquals((Integer) 2, listIterator.previous());
        Assert.assertEquals((Integer) 10, listIterator.previous());
        Assert.assertEquals((Integer) 1, listIterator.previous());
    }

        @Test(expected = NoSuchElementException.class)
    public void testAddNoSuchElementException() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>(1, 2, 3, 4);
        ListIterator<Integer> listIterator = myLinkedList.listIterator();
        listIterator.next();
        listIterator.next();
        listIterator.next();
        listIterator.next();
        listIterator.next();
    }
    @Test(expected = NoSuchElementException.class)
    public void testAddNoSuchElementExceptionPrevious() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>(1, 2, 3, 4);
        ListIterator<Integer> listIterator = myLinkedList.listIterator();
        listIterator.previous();
    }

    @Test
    public void testAdd2() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        ListIterator<Integer> listIterator = myLinkedList.listIterator();
        //добавляем первый элемент
        listIterator.add(10);
        Assert.assertEquals((Integer)10,myLinkedList.get(0));
        Assert.assertEquals((Integer)10,listIterator.next());
    }

    @Test
    public void testRemove() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>(1, 2, 3, 4);
        ListIterator<Integer> listIterator = myLinkedList.listIterator();
        listIterator.next();
        listIterator.next();
        listIterator.remove();
        Assert.assertFalse("2 не была удалена",myLinkedList.contains(2));
        Assert.assertEquals(3,myLinkedList.size());
        Assert.assertEquals((Integer)3,listIterator.next());
        Assert.assertEquals((Integer)4,listIterator.next());
    }

    @Test
    public void testRemove2() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>(1, 2, 3, 4);
        ListIterator<Integer> listIterator = myLinkedList.listIterator();
        listIterator.next();
        listIterator.next();
        listIterator.next();
        listIterator.next();
        listIterator.remove();
        Assert.assertEquals((Integer)3,myLinkedList.get(2));
        Assert.assertEquals(3,myLinkedList.size());
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveException() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>(1, 2, 3, 4);
        ListIterator<Integer> listIterator = myLinkedList.listIterator();
        listIterator.remove();
    }

    @Test
    public void testSetListIterator() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>(1, 2, 3, 4);
        ListIterator<Integer> listIterator = myLinkedList.listIterator();
        listIterator.next();
        listIterator.set(90);
        Assert.assertEquals((Integer)90, myLinkedList.get(0));
        listIterator.next();
        listIterator.set(90);
        Assert.assertEquals((Integer)90, myLinkedList.get(1));
        listIterator.next();
        listIterator.set(90);
        Assert.assertEquals((Integer)90, myLinkedList.get(2));
        listIterator.next();
        listIterator.set(90);
        Assert.assertEquals((Integer)90, myLinkedList.get(3));
    }


    @Test
    public void testRemove3() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>(1, 2, 3, 4);
        ListIterator<Integer> listIterator = myLinkedList.listIterator();
        listIterator.next();
        listIterator.next();
        listIterator.next();
        listIterator.previous();
        listIterator.remove();
        Assert.assertFalse("в коллекции не был удален элетент 2",myLinkedList.contains(2));
    }

}




