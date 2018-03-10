package myStructures;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;

public abstract class MyListTest {
    
    public  abstract <T> List<T> createList(T...args);

    @Test
    public void containsAllTest() {
        List<Integer> integers = createList(1, 2, 3);
        {
            List<Integer> integersToCheck = createList(1, 2);
            Assert.assertTrue("Коллекция " + integers.toString() + " не содержит все элементы коллекции " + integersToCheck.toString(), integers.containsAll(integersToCheck));
        }

        {
            List<Integer> integersToCheck = createList();
            Assert.assertFalse("Коллекция " + integers.toString() + " содержит элементы коллекции " + integersToCheck.toString(), integers.containsAll(integersToCheck));
        }

        {
            List<Integer> integers1 = createList();
            List<Integer> integersToCheck = createList(1, 2);
            Assert.assertFalse("Коллекция " + integers1.toString() + "  содержит элементы коллекции " + integersToCheck.toString(), integers1.containsAll(integersToCheck));
        }
    }

    @Test(expected = ClassCastException.class)
    public void containsAllClassCastExceptionTest() {
        List<Integer> integers = createList(1, 2, 3);
        List<String> integersToCheck = createList("one", "two");
        integers.containsAll(integersToCheck);
    }

    @Test(expected = NullPointerException.class)
    public void containsAllNPETest() {
        List<Integer> integers = createList(1, 2, 3);
        List<String> integersToCheck = null;
        integers.containsAll(integersToCheck);
    }

    @Test
    public void getElementTest() {
        List<Integer> myLinkedList = createList(1, 2, 3);
        Assert.assertEquals("получен некорректный первый (0) элемент, ожидаемое значение 1, фактическое " + myLinkedList.get(0), (Integer) 1, myLinkedList.get(0));
        Assert.assertEquals("получен некорректный элемент в середине коллекции, ожидаемое значение 2, фактическое " + myLinkedList.get(1), (Integer) 2, myLinkedList.get(1));
        Assert.assertEquals("получен некорректный последний элемент, ожидаемое значение 3, фактическое " + myLinkedList.get(2), (Integer) 3, myLinkedList.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getElementIndexOutOfBoundException() {
        List<Integer> myLinkedList = createList(1, 2, 3);
        myLinkedList.get(5);
    }

    // ошибка строка 61 как проверить на соответствик классов если эт-тов в листе нет
    @Test
    public void addAllTest() {
        List<Integer> myLinkedList = createList(1, 2, 3);
        {
            List<Integer> myLinkedList1 = createList();
            Set<Integer> mySet = new HashSet<>();
            mySet.add(5);
            mySet.add(10);
            Assert.assertTrue("элементы коллекции " + mySet.toString() + " не были добавлены либо некорректно добавлены " +
                    "в коллекцию (после изменения) " + myLinkedList.toString(), myLinkedList.addAll(mySet));
            Assert.assertEquals(5, myLinkedList.size());
            Assert.assertTrue("элементы коллекции " + mySet.toString() + " не были добавлены либо некорректно добавлены" +
                    " в коллекцию (после изменения) " + myLinkedList1.toString(), myLinkedList1.addAll(mySet));
            Assert.assertEquals(2, myLinkedList1.size());
        }
        {
            List<Integer> myLinkedList2 = createList(1, 2, 3);
            Set<Integer> mySet = new HashSet<>();
            Assert.assertTrue("элементы коллекции " + mySet.toString() + "  были успешно добавлены, результат добавления " +
                    "в коллекцию (после изменения) " + myLinkedList2.toString(), myLinkedList2.addAll(mySet));
            Assert.assertEquals(3, myLinkedList2.size());
        }
    }

    @Test
    public void testSet() {
        List<Integer> myLinkedList = createList(1, 2, 3);
        myLinkedList.set(0, 5);
        Assert.assertEquals("утанавливаем первый элемент в значение 5", (Integer) 5, myLinkedList.get(0));
        myLinkedList.set(1, 5);
        Assert.assertEquals("утанавливаем элемент в середине в значение 5", (Integer) 5, myLinkedList.get(1));
        myLinkedList.set(2, 5);
        Assert.assertEquals("утанавливаем последний элемент в значение 5", (Integer) 5, myLinkedList.get(2));
    }

    @Test(expected = NullPointerException.class)
    public void setNPETest() {
        List<Integer> myLinkedList = createList(1, 2, 3);
        myLinkedList.set(0, null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setIndexOutOfBoundTest() {
        List<Integer> myLinkedList = createList(1, 2, 3);
        myLinkedList.set(9, 2);
    }

    @Test
    public void sublistTest() {
        List<Integer> myLinkedList = createList(1, 2, 3, 24);
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
        List<String> testList = createList("echo", "hello").subList(0, 1);
        testList.add("mama");
    }

    @Test
    public void testListIteratorTraversing() {
        List<Integer> myLinkedList = createList(1, 2, 3, 4);
        ListIterator<Integer> listIterator = myLinkedList.listIterator();
        Assert.assertTrue("лист итератор при первом вызове hasNext (элементы в коллекции есть) некорректно отрабатывает, возвращая ложь", listIterator.hasNext());
        Assert.assertEquals("incorrect next index", 0, listIterator.nextIndex());
        Assert.assertEquals("incorrect prev index", -1, listIterator.previousIndex());
        Assert.assertEquals("incorrect 1 element", (Integer) 1, listIterator.next());
        Assert.assertEquals("incorrect prev index", 0, listIterator.previousIndex());
        Assert.assertEquals("incorrect next index", 1, listIterator.nextIndex());

        Assert.assertTrue("лист итератор при первом вызове hasNext (элементы в коллекции есть) некорректно отрабатывает, возвращая ложь", listIterator.hasNext());
        Assert.assertEquals("incorrect 2 element", (Integer) 2, listIterator.next());
        Assert.assertEquals("incorrect next index", 2, listIterator.nextIndex());

        Assert.assertTrue("лист итератор некорректно отрабатывает, возвращая ложь", listIterator.hasNext());
        Assert.assertEquals("incorrect 3 element", (Integer) 3, listIterator.next());
        Assert.assertEquals("incorrect prev index", 2, listIterator.previousIndex());
        Assert.assertEquals("incorrect next index", 3, listIterator.nextIndex());

        Assert.assertTrue("лист итератор некорректно отрабатывает, возвращая ложь", listIterator.hasNext());
        Assert.assertEquals("incorrect 4 element", (Integer) 4, listIterator.next());
        Assert.assertEquals("incorrect next index", 4, listIterator.nextIndex());

        Assert.assertTrue("лист итератор при первом вызове hasPrevious некорректно отрабатывает, возвращая ложь", listIterator.hasPrevious());
        Assert.assertEquals("incorrect prev index", 3, listIterator.previousIndex());
        Assert.assertEquals("incorrect 4 element", (Integer) 4, listIterator.previous());
        Assert.assertEquals("incorrect prev index", 2, listIterator.previousIndex());

        Assert.assertTrue("лист итератор при вызове hasPrevious некорректно отрабатывает, возвращая ложь", listIterator.hasPrevious());
        Assert.assertEquals("incorrect 3 element", (Integer) 3, listIterator.previous());
        Assert.assertEquals("incorrect prev index", 1, listIterator.previousIndex());
        Assert.assertEquals("incorrect prev index", 1, listIterator.previousIndex());

        Assert.assertTrue("лист итератор при вызове hasPrevious некорректно отрабатывает, возвращая ложь", listIterator.hasPrevious());
        Assert.assertEquals("incorrect 2 element", (Integer) 2, listIterator.previous());
        Assert.assertEquals("incorrect prev index", 0, listIterator.previousIndex());

        Assert.assertTrue("лист итератор при вызове hasPrevious некорректно отрабатывает, возвращая ложь", listIterator.hasPrevious());
        Assert.assertEquals("incorrect 2 element", (Integer) 1, listIterator.previous());
        Assert.assertEquals("incorrect prev index", -1, listIterator.previousIndex());
    }

    @Test
    public void testListIteratorRemove() {
        List<Integer> myLinkedList = createList(1, 2, 3, 4);
        ListIterator<Integer> listIterator = myLinkedList.listIterator(1);
        Assert.assertEquals("некорректо возвращен элемент вызовом next, должен быть 2", (Integer) 2, listIterator.next());

        ListIterator<Integer> listIterator1 = myLinkedList.listIterator(1);
        Assert.assertEquals("некорректо возвращен элемент вызовом prev, должен быть 1", (Integer) 1, listIterator1.previous());
        listIterator1.remove();
        Assert.assertTrue("некорректно произведено удаление элемента 1", !myLinkedList.contains(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void testListIteratorRemoveExceptionAdd() {
        List<Integer> myLinkedList = createList(1, 2, 3, 4);
        ListIterator<Integer> listIterator1 = myLinkedList.listIterator(0);
        listIterator1.previous();
    }

    @Test
    public void testListIteratorAddTraversingNext() {
        List<Integer> myLinkedList = createList(1, 2, 3, 4);
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
        listIterator.add(10);
        Assert.assertEquals((Integer) 10, myLinkedList.get(5));
        listIterator.add(10);
        Assert.assertEquals((Integer) 10, myLinkedList.get(6));

    }

    @Test
    public void testAddTraversingPrevious() {
        List<Integer> myLinkedList = createList(1, 2, 3, 4);
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
    public void testAddNoSuchElementExceptionNext() {
        List<Integer> myLinkedList = createList(1, 2, 3, 4);
        ListIterator<Integer> listIterator = myLinkedList.listIterator();
        listIterator.next();
        listIterator.next();
        listIterator.next();
        listIterator.next();
        listIterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testAddNoSuchElementExceptionPrevious() {
        List<Integer> myLinkedList = createList(1, 2, 3, 4);
        ListIterator<Integer> listIterator = myLinkedList.listIterator();
        listIterator.previous();
    }

    @Test
    public void testRemove() {
        List<Integer> myLinkedList = createList(1, 2, 3, 4);
        ListIterator<Integer> listIterator = myLinkedList.listIterator();
        listIterator.next();
        listIterator.next();
        listIterator.remove();
        Assert.assertFalse("2 не была удалена", myLinkedList.contains(2));
        Assert.assertEquals(3, myLinkedList.size());
        Assert.assertEquals((Integer) 3, listIterator.next());
        Assert.assertEquals((Integer) 4, listIterator.next());
    }

    @Test
    public void testRemove1() {
        List<Integer> myLinkedList = createList(1, 2, 3, 4);
        ListIterator<Integer> listIterator = myLinkedList.listIterator();
        listIterator.next();
        listIterator.next();
        listIterator.next();
        listIterator.next();
        listIterator.remove();
        Assert.assertEquals((Integer) 3, myLinkedList.get(2));
        Assert.assertEquals(3, myLinkedList.size());
    }

    @Test
    public void testRemove2() {
        List<Integer> myLinkedList = createList(1, 2, 3, 4);
        ListIterator<Integer> listIterator = myLinkedList.listIterator();
        listIterator.next();
        listIterator.next();
        listIterator.next();
        listIterator.previous();
        listIterator.remove();
        Assert.assertFalse("в коллекции не был удален элетент 2", myLinkedList.contains(2));
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveException() {
        List<Integer> myLinkedList = createList(1, 2, 3, 4);
        ListIterator<Integer> listIterator = myLinkedList.listIterator();
        listIterator.remove();
    }

    @Test
    public void testSetListIterator() {
        List<Integer> myLinkedList = createList(1, 2, 3, 4);
        ListIterator<Integer> listIterator = myLinkedList.listIterator();
        listIterator.next();
        listIterator.set(90);
        Assert.assertEquals((Integer) 90, myLinkedList.get(0));
        listIterator.next();
        listIterator.set(90);
        Assert.assertEquals((Integer) 90, myLinkedList.get(1));
        listIterator.next();
        listIterator.set(90);
        Assert.assertEquals((Integer) 90, myLinkedList.get(2));
        listIterator.next();
        listIterator.set(90);
        Assert.assertEquals((Integer) 90, myLinkedList.get(3));
    }

    @Test(expected = NullPointerException.class)
    public void testListIteratorSetNPE() {
        List<Integer> myLinkedList = createList(1, 2, 3, 4);
        ListIterator<Integer> listIterator1 = myLinkedList.listIterator(0);
        listIterator1.set(10000);
    }

    @Test
    public void testLastIndexOf() {
        List<Integer> integers = createList();
        integers.add(0);
        integers.add(0);
        integers.add(1);
        integers.add(2);
        integers.add(1);
        integers.add(3);
        Assert.assertEquals("Method returned incorrect last index, it have to be 1", (Integer) 1, (Integer) integers.lastIndexOf(0));
        Assert.assertEquals("Method returned incorrect last index, it have to be 4", (Integer) 4, (Integer) integers.lastIndexOf(1));
        Assert.assertEquals("Method returned incorrect last index, it have to be 5", (Integer) 5, (Integer) integers.lastIndexOf(3));
        Assert.assertEquals("Method returned incorrect last index, it have to be -1", (Integer) (-1), (Integer) integers.lastIndexOf(100));
    }

    @Test(expected = ClassCastException.class)
    public void testLastIndexOfClassCastException() {
        List<Integer> integers = createList();
        integers.add(0);
        integers.add(1);
        integers.add(2);
        integers.add(1);
        integers.add(3);
        integers.lastIndexOf('v');
    }

    @Test(expected = NullPointerException.class)
    public void testLastIndexOfClassCastNPE() {
        List<Integer> integers = createList();
        integers.add(0);
        integers.add(1);
        integers.add(3);
        integers.lastIndexOf(null);
    }

    @Test
    public void testIndexOf() {
        List<Integer> integers = createList();
        integers.add(1);
        integers.add(4);
        integers.add(6);
        integers.add(9);
        integers.add(0);
        integers.add(11);
        Assert.assertEquals("", (Integer) 3, (Integer) integers.indexOf(9));
        Assert.assertEquals("", (Integer) 0, (Integer) integers.indexOf(1));
        Assert.assertEquals("", (Integer) 5, (Integer) integers.indexOf(11));
    }

    @Test(expected = NullPointerException.class)
    public void indexOfNPE() {
        List<Integer> integers = createList();
        integers.add(1);
        integers.add(4);
        integers.add(0);
        integers.add(11);
        integers.indexOf(null);
    }

    @Test(expected = ClassCastException.class)
    public void indexOfClasCastException() {
        List<Integer> integers = createList();
        integers.add(1);
        integers.add(4);
        integers.add(0);
        integers.add(11);
        integers.indexOf("o");
    }

    @Test
    public void removeAllTest() {
        List<Integer> integers = createList();
        integers.add(1);
        integers.add(4);
        integers.add(0);
        integers.add(11);

        List<Integer> toBeRemovedFromList = createList();
        toBeRemovedFromList.add(1);
        toBeRemovedFromList.add(2);

        integers.removeAll(toBeRemovedFromList);
        Assert.assertEquals(3, integers.size());
        Assert.assertFalse(integers.contains(1));
        Assert.assertTrue(integers.contains(4));
        Assert.assertTrue(integers.contains(0));
        Assert.assertTrue(integers.contains(11));

        List<Integer> toBeRemovedFromList1 = createList();
        toBeRemovedFromList1.add(4);
        toBeRemovedFromList1.add(11);
        integers.removeAll(toBeRemovedFromList1);
        Assert.assertEquals(1, integers.size());
        Assert.assertFalse(integers.contains(4));
        Assert.assertFalse(integers.contains(11));
        Assert.assertTrue(integers.contains(0));

        List<Integer> removeAllRealyAll = createList(1, 2, 3, 4);
        List<Integer> toRemove = createList(1, 2, 3, 4);

        removeAllRealyAll.removeAll(toRemove);
    }

    @Test(expected = NullPointerException.class)
    public void RemoveAllNPE() {
        List<Integer> integers = createList();
        integers.add(1);
        integers.add(4);
        integers.add(0);
        integers.add(11);
        List<Integer> integers1 = null;
        integers.removeAll(integers1);
    }

    @Test(expected = ClassCastException.class)
    public void removeAllClasCastException() {
        List<Integer> integers = createList();
        integers.add(1);
        integers.add(4);
        integers.add(0);
        integers.add(11);
        List<String> strings = createList();
        strings.add("ooooo");
        integers.removeAll(strings);
    }

    @Test
    public void clearTest() {
        List<Integer> integers = createList();
        integers.add(1);
        integers.add(4);
        integers.add(0);
        integers.add(11);
        integers.clear();
        Assert.assertEquals(0, integers.size());
    }

    @Test
    public void addAllAtPosition() {
        {
            List<Integer> integers = createList(1, 2, 3, 4, 5);
            List<Integer> integers1 = createList(6, 7, 8, 9, 10);
            Assert.assertTrue(integers.addAll(3, integers1));
            Assert.assertEquals((Integer) 10, (Integer) integers.size());
            Assert.assertEquals((Integer) 6, integers.get(3));
        }
        {
            List<Integer> integers = createList(1, 2, 3, 4, 5);
            List<Integer> integers1 = createList(6, 7, 8, 9, 10);
            Assert.assertTrue(integers.addAll(0, integers1));
            Assert.assertEquals((Integer) 10, (Integer) integers.size());
            Assert.assertEquals((Integer) 6, integers.get(0));
        }
        {
            List<Integer> integers = createList(1, 2, 3, 4, 5);
            List<Integer> integers1 = createList(6, 7, 8, 9, 10);
            Assert.assertTrue(integers.addAll(5, integers1));
            Assert.assertEquals((Integer) 10, (Integer) integers.size());
            Assert.assertEquals((Integer) 6, integers.get(5));
        }
    }

    @Test(expected = NullPointerException.class)
    public void addAllNPE() {
        List<Integer> integers = createList(1, 2, 3, 4, 5);
        List<Integer> integers1 = null;
        integers.addAll(5, integers1);
    }

    @Test(expected = NoSuchElementException.class)
    public void addAllNSE() {
        List<Integer> integers = createList(1, 2, 3, 4, 5);
        List<Integer> integers1 = createList();
        integers.addAll(5, integers1);
    }

    @Test
    public void retainAllTest() {
        {
            List<Integer> integers = createList(1, 2, 3, 4, 5);
            List<Integer> integers1 = createList(1, 2, 3, 4, 5);
            Assert.assertFalse(integers.retainAll(integers1));
            Assert.assertEquals(5, integers.size());
        }
        {
            List<Integer> integers = createList(1, 2, 3, 4, 5);
            List<Integer> integers1 = createList(2, 3, 4, 5);
            Assert.assertTrue(integers.retainAll(integers1));
            Assert.assertEquals(4, integers.size());
            Assert.assertFalse(integers.contains(1));
        }
        {
            List<Integer> integers = createList(1, 2);
            List<Integer> integers1 = createList();
            Assert.assertTrue(integers.retainAll(integers1));
            Assert.assertEquals(0, integers.size());
            Assert.assertFalse(integers.contains(1));
            Assert.assertFalse(integers.contains(2));
        }
        {
            List<Integer> integers = createList(1, 2, 3, 4, 5);
            List<Integer> integers1 = createList();
            Assert.assertFalse(integers1.retainAll(integers));
        }

    }

    @Test
    public void testToArray() {
        List<Integer> myLinkedList = createList(1, 2, 3);
        Object[] objects = myLinkedList.toArray();
        Assert.assertEquals(3, objects.length);
        for (Object obj : objects) {
            System.out.println(obj);
        }
    }

    @Test(expected = NullPointerException.class)
    public void testToArrayWithParameterNull() {
        List<Integer> myLinkedList = createList(1, 2, 3);
        Integer[] destinationArraay = null;
        myLinkedList.toArray(destinationArraay);

    }

    @Test(expected = ArrayStoreException.class)
    public void testToArrayWithParameterStoreException() {
        List<Integer> myLinkedList = createList(1, 2, 3);
        String[] destinationArraay = new String[3];
        myLinkedList.toArray(destinationArraay);

    }

    @Test
    public void testToArrayWithParameter_equalCases() {
        List<Integer> myLinkedList = createList(1, 2, 3);
        Integer[] destinationArray = new Integer[3];
        myLinkedList.toArray(destinationArray);
        Assert.assertEquals((Integer) 1, destinationArray[0]);
        Assert.assertEquals((Integer) 2, destinationArray[1]);
        Assert.assertEquals((Integer) 3, destinationArray[2]);
        for (Integer integer : destinationArray) {
            System.out.println(integer);
        }
    }

    @Test
    public void testToArrayWithParameter_destSizeIsHigher() {
        System.out.println("destination array size is higher");
        List<Integer> myLinkedList = createList(1, 2, 3);
        Integer[] destinationArray = new Integer[5];
        myLinkedList.toArray(destinationArray);
        Assert.assertEquals(5, destinationArray.length);
        Assert.assertEquals((Integer) 1, destinationArray[0]);
        Assert.assertEquals((Integer) 2, destinationArray[1]);
        Assert.assertEquals((Integer) 3, destinationArray[2]);
        Assert.assertEquals(null, destinationArray[3]);
        Assert.assertEquals(null, destinationArray[4]);
        for (Integer integer : destinationArray) {
            System.out.println(integer);
        }
    }

    @Test
    public void testToArrayWithParameter_destSizeIsLess() {
        List<Integer> myLinkedList = createList();
        myLinkedList.add(1);
        myLinkedList.add(1);
        myLinkedList.add(1);
        Object[] destinationArray = new String[1];
        myLinkedList.toArray(destinationArray);
        for (Object integer : destinationArray) {//почему при размере меньше листа, выдает налл
            System.out.println(integer);
        }
    }

    @Test
    public void removeTest() {
        {
            List<Integer> integerList = createList(1, 2, 3);
            Assert.assertEquals((Integer) 1, integerList.remove(0));
            Assert.assertEquals((Integer) 2, (Integer) integerList.size());
        }
        {
            List<Integer> integerList = createList(1, 2, 3);
            Assert.assertEquals((Integer) 3, integerList.remove(2));
            Assert.assertEquals((Integer) 2, (Integer) integerList.size());
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeTestException() {
        List<Integer> integerList = createList(1, 2, 3);
        integerList.remove(9);

    }

    @Test
    public void addAtPositionTest() {
        {
            List<Integer> integerList = createList(1, 2, 3);
            integerList.add(0, 100);
            Assert.assertEquals((Integer) 4, (Integer) integerList.size());
            Assert.assertEquals((Integer) 100, (Integer) integerList.get(0));
        }
        {
            List<Integer> integerList = createList(1, 2, 3);
            integerList.add(2, 100);
            Assert.assertEquals((Integer) 4, (Integer) integerList.size());
            Assert.assertEquals((Integer) 100, (Integer) integerList.get(2));
        }
        {
            List<Integer> integerList = createList(1, 2, 3);
            integerList.add(3, 100);
            Assert.assertEquals((Integer) 4, (Integer) integerList.size());
            Assert.assertEquals((Integer) 100, (Integer) integerList.get(3));
        }
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void addAtPositionException(){
        List<Integer> integerList = createList(1, 2, 3);
        integerList.add(20, 100);
    }
}




