package com.nixsolutions.utils;

import com.nixsolutions.model.Vehicle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code Garage} class
 *
 * @author Strunin Dmytro
 * @version 1.0
 */
public class Garage<T extends Vehicle> implements Iterable<T> {
    private int size;
    private Node head;

    private class Node {
        private T data;
        private Node prev;
        private Node next;
        private final int restylingNumber;
        private LocalDateTime date;

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }

        public Node(T data, Node prev, Node next, int restylingNumber) {
            this.data = data;
            this.prev = prev;
            this.next = next;
            this.restylingNumber = restylingNumber;
            this.date = LocalDateTime.now();
        }
    }

    public void add(T vehicle, int restylingNumber) {
        if (head == null) {
            head = new Node(vehicle, null, null, restylingNumber);
        } else {
            head = new Node(vehicle, null, head, restylingNumber);
            head.next.prev = head;
        }
        size++;
    }

    private Node searchNodeByRestylingNumber(int restylingNumber) {
        Node tmp = head;
        while (tmp != null) {
            if (tmp.restylingNumber == restylingNumber) {
                return tmp;
            }
            tmp = tmp.next;
        }
        throw new IllegalArgumentException();
    }

    private int searchRestylingNumberByVehicle(T t) {
        Node tmp = head;
        while (tmp != null) {
            if (t.equals(tmp.data)) {
                return tmp.restylingNumber;
            }
            tmp = tmp.next;
        }
        throw new IllegalArgumentException();
    }

    private LocalDateTime searchDateByVehicle(T t) {
        Node tmp = head;
        while (tmp != null) {
            if (t.equals(tmp.data)) {
                return tmp.date;
            }
            tmp = tmp.next;
        }
        throw new IllegalArgumentException();
    }

    public T getByRestylingNumber(int restylingNumber) {
        return searchNodeByRestylingNumber(restylingNumber).data;
    }

    public T removeByRestylingNumber(int restylingNumber) {
        Node tmp = searchNodeByRestylingNumber(restylingNumber);
        if (tmp.prev == null) {
            head = tmp.next;
        } else {
            tmp.prev.next = tmp.next;
        }
        if (tmp.next != null) {
            tmp.next.prev = tmp.prev;
        }
        size--;
        return tmp.data;
    }

    public T setByRestylingNumber(int restylingNumber, T vehicle) {
        return searchNodeByRestylingNumber(restylingNumber).data = vehicle;
    }

    public int getAmountRestylingNumbers() {
        return size;
    }

    public String getFirstIssueDate() {
        Node tmp = head;
        LocalDateTime localDateTime = LocalDateTime.now();
        while (tmp != null) {
            if (localDateTime.isBefore(tmp.date)) {
                localDateTime = tmp.date;
            }
            tmp = tmp.next;
        }
        return localDateTime.format(DateTimeFormatter.ofPattern("d.MM.yyyy H:mm"));
    }

    public String getLastIssueDate() {
        Node tmp = head;
        LocalDateTime localDateTime = LocalDateTime.now();
        while (tmp != null) {
            if (localDateTime.isAfter(tmp.date)) {
                localDateTime = tmp.date;
            }
            tmp = tmp.next;
        }
        return localDateTime.format(DateTimeFormatter.ofPattern("d.MM.yyyy H:mm"));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void sort(Comparator<? super T> c) {
        Object[] array = this.toArray();
        Arrays.sort(array, (Comparator) c.reversed());
        Garage<T> tmp = new Garage<>();
        int restylingNumberTmp;
        for (Object e : array) {
            restylingNumberTmp = searchRestylingNumberByVehicle((T) e);
            tmp.add((T) e, restylingNumberTmp);
            tmp.searchNodeByRestylingNumber(restylingNumberTmp).date = searchDateByVehicle((T) e);
        }
        head = tmp.head;
    }

    private Object[] toArray() {
        Iterator<T> iterator = this.iterator();
        Object[] array = new Object[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = iterator.next();
        }
        return array;
    }

    @Override
    public String toString() {
        Node stNode = head.next;
        StringBuilder stringBuilder = new StringBuilder("Garage{" +
                "size=" + size +
                ", node=" + head);
        while (stNode != null) {
            stringBuilder.append(", node=").append(stNode);
            stNode = stNode.next;
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new GarageIterator();
    }

    private class GarageIterator implements Iterator<T> {
        private Node cursor = head;

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public T next() {
            T tmp = cursor.data;
            if (!hasNext()) {
                throw new NoSuchElementException("iterator not have next element");
            }
            cursor = cursor.next;
            return tmp;
        }
    }
}
